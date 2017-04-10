package bs.ecust.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bs.ecust.SecurityUtils;
import bs.ecust.SolverStatusEnum;
import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Anlz;
import bs.ecust.domain.db.Itr;
import bs.ecust.domain.db.ItrEntVl;
import bs.ecust.repository.AnlzRepository;
import bs.ecust.repository.InstncRepository;
import bs.ecust.repository.ItrEntVlRepository;
import bs.ecust.repository.ItrRepository;
import bs.ecust.repository.SpcEntRepository;
import cs.ecust.domain.view.TmpAnlz;


@Service("itrEntVlService")
@Transactional(readOnly=true)
public class ItrEntVlServiceImpl implements  ItrEntVlService {
	
	@Autowired private ItrEntVlRepository itrEntVlRepository;
	@Autowired private InstncRepository instncRepository;
	@Autowired private SecurityUtils securityUtils;
	@Autowired private ItrRepository itrRepository;
	@Autowired private SpcEntRepository spcEntRepository;
	@Autowired
	@Qualifier("solverSim")
	private Solver solver;
	
	@Override
	@Transactional(readOnly=false)
	public void saveValue(Long itrId, Long entId, Long idCrtrn, Float value) {
		ItrEntVl ev = new ItrEntVl();
		ev.setItr(itrRepository.findOne(itrId));
		ev.setObjectId(idCrtrn);
		ev.setObjectId2(0l);
		ev.setValue(value);
		ev.setSpcEnt(spcEntRepository.findOne(entId));
		itrEntVlRepository.save(ev);
		
	}
	


}
