package bs.ecust.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.ecust.SecurityUtils;
import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Altrntv;
import bs.ecust.domain.db.AltrntvVl;
import bs.ecust.domain.db.AltrntvVlId;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Crtrn;
import bs.ecust.domain.db.Instnc;
import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;
import bs.ecust.repository.AltrntvRepository;
import bs.ecust.repository.AltrntvVlRepository;
import bs.ecust.repository.AtrbtRepository;
import bs.ecust.repository.CrtrnRepository;
import bs.ecust.repository.InstncRepository;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.repository.UsrRepository;
import cs.ecust.domain.view.TmpAltrntv;
import cs.ecust.domain.view.TmpAtrbt;
import cs.ecust.domain.view.TmpInstance;
import cs.ecust.domain.view.TmpPrblm;

@Service("instanceService")
@Transactional(readOnly=true)
public class InstanceServiceImpl implements InstanceService {
	
	@Autowired private PrblmRepository prblmRepository;
	@Autowired private AtrbtRepository atrbtRepository;
	@Autowired private AltrntvRepository altrntvRepository;
	@Autowired private AltrntvVlRepository altrntvVlRepository;
	@Autowired private InstncRepository instncRepository;
	@Autowired private SecurityUtils securityUtils;
	@Autowired private CrtrnRepository crtrnRepository;


	@Override
	@Transactional(readOnly=false)
	public void saveTmpInstance(TmpInstance tmpInstance) {
		Instnc instance = new Instnc();
		instance.setSnm(tmpInstance.getShrtnm());
		instance.setNm(tmpInstance.getShrtnm());
		instance.setDscr(tmpInstance.getDscrptn());
		instance.setCreated(new Date());
		instance.setPblsh(StatusEnum.COMMITTED.getStatusCode());
		Prblm prblm = prblmRepository.findOne(tmpInstance.getProblemId());
		instance.setPrblm(prblm);
		instance.setUsr(securityUtils.getCurrentDBUser());
		Map<Long,Long> atrbtMap = new LinkedHashMap<Long,Long>();
		Long i=0l;
		for(Atrbt atrbt:atrbtRepository.findByProblemId(tmpInstance.getProblemId()))
		{
			atrbtMap.put(atrbt.getIdAtrbt(),i);
			i++;
		}
		Long cSeq=0l;
		for(Long idAtrbt: tmpInstance.getAtrbtIds())
		{
			Atrbt atrbt = atrbtRepository.findOne(idAtrbt);
			Crtrn crtrn = new Crtrn();
			crtrn.setAtrbt(atrbt);
			crtrn.setInstnc(instance);
			int seq = atrbtMap.get(idAtrbt).intValue();
			crtrn.setSnm(tmpInstance.getCrtrnSnms().get(seq));
			crtrn.setNm(tmpInstance.getCrtrnNms().get(seq));
			crtrn.setType(tmpInstance.getTypes().get(seq));
			crtrn.setSeq(cSeq);
			crtrn.setOrgTarget(0f);
			crtrn.setLvl(0l);
			crtrn.setTopSet(0l);
			crtrnRepository.save(crtrn);
			cSeq++;
		}
	
		instance = instncRepository.save(instance);
	}
	



}
