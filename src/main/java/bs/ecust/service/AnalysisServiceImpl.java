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
import bs.ecust.repository.AnlzRepository;
import bs.ecust.repository.InstncRepository;
import bs.ecust.repository.ItrRepository;
import cs.ecust.domain.view.TmpAnlz;


@Service("analysisService")
@Transactional(readOnly=true)
public class AnalysisServiceImpl implements  AnalysisService {
	
	@Autowired private AnlzRepository anlzRepository;
	@Autowired private InstncRepository instncRepository;
	@Autowired private SecurityUtils securityUtils;
	@Autowired private ItrRepository itrRepository;
	@Autowired
	@Qualifier("solverSim")
	private Solver solver;
	@Override
	@Transactional(readOnly=false)
	public void saveTmpAnalysis(TmpAnlz tmpAnlz) {
		Anlz anlz = new Anlz();
		anlz.setSnm(tmpAnlz.getShrtnm());
		anlz.setNm(tmpAnlz.getLngnm());
		anlz.setDscr(tmpAnlz.getDscrptn());
		anlz.setCreated(new Date());
		anlz.setUsr(securityUtils.getCurrentDBUser());
		anlz.setInstnc(instncRepository.findOne(tmpAnlz.getInstanceId()));
		anlz.setPblsh(StatusEnum.COMMITTED.getStatusCode());
		anlz = anlzRepository.save(anlz);
		// create intial iteration: method: Objective Choice (APW, 2007)
		//todo: create inital itration
		Itr itr = new Itr();
		itr.setAnlz(anlz);
		itr.setCreated(new Date());
		//method: Objective Choice (APW, 2007)
		itr.setIdMethod(1l);
		itr.setNote("ini");
		//itr.sets
		itr.setStatus(SolverStatusEnum.INITIAL.elementCode());
		itr.setUsr(securityUtils.getCurrentDBUser());
		itr.setLastmdfy(new Date());
		itr = itrRepository.save(itr);
		try
		{
			solver.Call(itr.getIdItr());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	



}
