package bs.ecust.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;
import bs.ecust.repository.AltrntvRepository;
import bs.ecust.repository.AltrntvVlRepository;
import bs.ecust.repository.AtrbtRepository;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.repository.UsrRepository;
import cs.ecust.domain.view.TmpAltrntv;
import cs.ecust.domain.view.TmpAtrbt;
import cs.ecust.domain.view.TmpPrblm;

@Service("problemService")
@Transactional(readOnly=true)
public class ProblemServiceImpl implements ProblemService {
	
	@Autowired private PrblmRepository prblmRepository;
	@Autowired private AtrbtRepository atrbtRepository;
	@Autowired private AltrntvRepository altrntvRepository;
	@Autowired private AltrntvVlRepository altrntvVlRepository;
	@Autowired private SecurityUtils securityUtils;

	@Override
	@Transactional(readOnly=false)
	public void createProblem(TmpPrblm pl) {
	
		Prblm prblm = new Prblm();
		prblm.setCreated(new Date());
		prblm.setDscr(pl.getDscrptn());
		prblm.setSnm(pl.getShrtnm());
		if(pl.getname()==null||pl.getname().isEmpty())
		{
			prblm.setNm(pl.getShrtnm());
		}
		else
		{
			prblm.setNm(pl.getname());
		}
		prblm.setPblsh(StatusEnum.COMMITTED.getStatusCode());
		prblm.setUsr(securityUtils.getCurrentDBUser());
		prblm = prblmRepository.save(prblm);
		List<Long> atrbtIds = new ArrayList<Long>();
		for(TmpAtrbt tmpAtrbt:pl.getAtrbts())
		{
			Atrbt  atrbt = new Atrbt();
			atrbt.setDscr(tmpAtrbt.getDscrptn());
			atrbt.setSnm(tmpAtrbt.getShrtnm());
			if(tmpAtrbt.getLngnm()==null||tmpAtrbt.getLngnm().isEmpty())
			{
				atrbt.setNm(tmpAtrbt.getShrtnm());
			}
			else
			{
				atrbt.setNm(tmpAtrbt.getLngnm());
			}
			atrbt.setUnit(tmpAtrbt.getUnit());
			atrbt.setPrblm(prblm);
			atrbt = atrbtRepository.save(atrbt);
		
			atrbtIds.add(atrbt.getIdAtrbt());
		}
		
		for(TmpAltrntv tmpAltrntv: pl.getAltrntvs())
		{
			Altrntv altrntv = new Altrntv();
			altrntv.setDscr(tmpAltrntv.getDscrptn());
			altrntv.setNm(tmpAltrntv.getLngnm());
			altrntv.setSnm(tmpAltrntv.getShrtnm());
			altrntv.setIdPrblm(prblm.getIdPrblm());
			altrntv = altrntvRepository.save(altrntv);
//			AltrntvVl
			int i=0;
			for(Double vl: tmpAltrntv.getValues())
			{
				AltrntvVl altrntvVl = new AltrntvVl();
				AltrntvVlId id = new AltrntvVlId();
				id.setIdAltrntv(altrntv.getIdAltrntv());
				id.setIdAtrbt(atrbtIds.get(i));
				altrntvVl.setId(id);
				altrntvVl.setOrgValue(vl.floatValue());
				altrntvVlRepository.save(altrntvVl);
				i++;
			}
		}
		
	}

	@Override
	public String[][] getProblemVls(Long problemId) {
		List<Atrbt> atrbts = atrbtRepository.findByProblemId(problemId);
		List<Altrntv> altrntvs = altrntvRepository.findByProblemId(problemId);
		String[][] vls = new String[altrntvs.size()+1][atrbts.size()+1];
		for(int h=1;h<atrbts.size()+1;h++)
		{
			vls[0][0] =" ";
			vls[0][h] = atrbts.get(h-1).getSnm();
		}
		for(int i=1;i<altrntvs.size()+1;i++)
		{
			vls[i][0] =altrntvs.get(i-1).getSnm();
			for(int j=1; j<atrbts.size()+1;j++)
			{
				AltrntvVlId id = new AltrntvVlId();
				id.setIdAltrntv(altrntvs.get(i-1).getIdAltrntv());
				id.setIdAtrbt(atrbts.get(j-1).getIdAtrbt());
				AltrntvVl altrntvVl = altrntvVlRepository.findOne(id);
				vls[i][j] = ""+ altrntvVl.getOrgValue();
			}
		}
		return vls;
	}
	



}
