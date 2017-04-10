package bs.ecust.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.ecust.SolverStatusEnum;
import bs.ecust.controller.ProblemController;
import bs.ecust.domain.db.Altrntv;
import bs.ecust.domain.db.AltrntvVl;
import bs.ecust.domain.db.Crtrn;
import bs.ecust.domain.db.Itr;
import bs.ecust.domain.db.ItrAlter;
import bs.ecust.domain.db.ItrAlterId;
import bs.ecust.domain.db.ItrBoxplot;
import bs.ecust.domain.db.ItrBoxplotId;
import bs.ecust.domain.db.ItrDots;
import bs.ecust.domain.db.ItrDotsId;
import bs.ecust.repository.ItrRepository;
import bs.ecust.repository.AltrntvRepository;
import bs.ecust.repository.AltrntvVlRepository;
import bs.ecust.repository.CrtrnRepository;
import bs.ecust.repository.ItrAlterRepository;
import bs.ecust.repository.ItrBoxplotRepository;
import bs.ecust.repository.ItrDotsRepository;


@Service("solverSim")
public class SolverSimImpl implements Solver {
	private static final Log logger = LogFactory.getLog(ProblemController.class);
	
	@Value("${solver.path}")
	private String solverPath;
	@Value("${solver.version}")
	private String solverVersion;
	@Value("${solver.schema}")
	private String solverSchema;
	@Autowired private ItrRepository itrRepository;
	@Autowired private  CrtrnRepository crtrnRepository;
	@Autowired private AltrntvVlRepository altrntvVlRepository;
	@Autowired private ItrDotsRepository itrDotsRepository;
	@Autowired private ItrBoxplotRepository itrBoxplotRepository;
	@Autowired private ItrEntVlService itrEntVlService;
	@Autowired private AltrntvRepository altrntvRepository;
	@Autowired private ItrAlterRepository itrAlterRepository;
	@Override
	@Transactional(readOnly=false)
	public  boolean Call(long itrId) throws Exception {
		String command = solverPath + solverVersion + " -itr_id " + itrId + " -schema " + solverSchema;
		//logger.debug("call solver: " + command);
		Itr itr = itrRepository.findOne(itrId);
		List<Crtrn> criteria = crtrnRepository.findByInstanceId(itr.getAnlz().getInstnc().getIdInstnc());
		double best_value = -1;
		Altrntv best_altrntv = new Altrntv();
		if(itr.getItr()==null) // root itr
		{
			for(Crtrn crtrn: criteria)
			{
				System.out.println("crtrn: " + crtrn.getSnm());
				List<AltrntvVl> vls = altrntvVlRepository.findByAttributeIdOrderByOrgValue(crtrn.getAtrbt().getIdAtrbt());
				double min = vls.get(0).getOrgValue();
				double max = vls.get(vls.size() - 1)
						.getOrgValue();
				double distance = max - min;
				long tmpPos = -1l;
				String tmplbl = "";
				long nDots = 0;
				for(AltrntvVl v:vls)
				{
					double value = v.getOrgValue();
					long pos = (long) (((value - min) / distance) * 1000);
					if (crtrn.getType().equalsIgnoreCase("max")) {

					} else {
						pos = 1000 - pos;
					}
			
					System.out.println("save ItrAlter: " + v.getAltrntv().getSnm() +", pos: " + pos);
					ItrAlter itrAlter  = new ItrAlter();
					ItrAlterId aId = new ItrAlterId();
					aId.setIdAltrntv(v.getAltrntv().getIdAltrntv());
					aId.setIdCrtrn(crtrn.getIdCrtrn());
					aId.setIdItr(itr.getIdItr());
					itrAlter.setId(aId);
					itrAlter.setPos(pos);
					itrAlter.setCaf(pos/1000f);
					itrAlterRepository.save(itrAlter);
				
					if(tmpPos==-1l)
					{
							tmpPos = pos;
							tmplbl = (pos / 10d) + "% "
									+ v.getAltrntv().getSnm()
									+ " " + v.getOrgValue() + " ["
									+ v.getAtrbt().getUnit()
									+ "]<br/>";
							nDots = 1;

					}
					else
					{
						if(tmpPos==pos)
						{

							tmplbl = tmplbl
									+ (pos / 10d)
									+ "% "
									+ v.getAltrntv().getSnm()
									+ " "
									+ v.getOrgValue()
									+ " ["
									+ v.getAtrbt().getUnit() + "]<br/>";
							nDots++;
						
						}
						else
						{

							ItrDots itrDots = new ItrDots();
							ItrDotsId id = new ItrDotsId();
							id.setIdCrtrn(crtrn.getIdCrtrn());
							id.setIdItr(itrId);
							id.setPos(tmpPos);
							itrDots.setId(id);
							itrDots.setItr(itr);
							itrDots.setCrtrn(crtrn);
							itrDots.setLabel(tmplbl);
							itrDots.setNdots(nDots);
							System.out.println("save itrDots: " + crtrn.getSnm() +", pos: " + tmpPos);
							itrDots = itrDotsRepository.save(itrDots);
							
							tmpPos = pos;
							tmplbl = (pos / 10d)
									+ "% "
									+ v.getAltrntv().getSnm()
									+ " "
									+ v.getOrgValue()
									+ " ["
									+ v.getAtrbt().getUnit()  + "]<br/>";
							nDots = 1;
						
						}
						
					}
					

				}
				
				ItrDots itrDots = new ItrDots();
				ItrDotsId did = new ItrDotsId();
				did.setIdCrtrn(crtrn.getIdCrtrn());
				did.setIdItr(itrId);
				did.setPos(tmpPos);
				itrDots.setId(did);
				itrDots.setItr(itr);
				itrDots.setCrtrn(crtrn);
				itrDots.setLabel(tmplbl);
				itrDots.setNdots(nDots);
				System.out.println("save itrDots: " + crtrn.getSnm() +", pos: " + tmpPos);
				itrDots = itrDotsRepository.save(itrDots);
				
				
				ItrBoxplot itrBoxplot = new ItrBoxplot();
				ItrBoxplotId id = new ItrBoxplotId();
				id.setIdCrtrn(crtrn.getIdCrtrn());
				id.setIdItr(itrId);
				itrBoxplot.setId(id);
				itrBoxplot.setLft(250l);
				itrBoxplot.setMdl(500l);
				itrBoxplot.setRght(750l);
				itrBoxplotRepository.save(itrBoxplot);
				
				//relative importance
				itrEntVlService.saveValue(itrId, 10l, crtrn.getIdCrtrn(), 5f);
				//Criteria Improvement
				itrEntVlService.saveValue(itrId, 11l, crtrn.getIdCrtrn(), 2f);
				
				
			   List<Altrntv> alternativeLst=altrntvRepository.findByProblemId(itr.getAnlz().getInstnc().getPrblm().getIdPrblm());
				for (Altrntv altrntv : alternativeLst) {
					float value = (float) Math.random();
					if (value >= best_value) {
						best_value = value;
						best_altrntv = altrntv;

					}

					// store ranking measure
					itrEntVlService.saveValue(itrId, 36l, crtrn.getIdCrtrn(), value);
				}
	
				
			}
	
	
			
			itrEntVlService.saveValue(itrId, 31l,best_altrntv.getIdAltrntv(),Float.valueOf(best_altrntv.getIdAltrntv()));
			
			
		}
		itr.setStatus(SolverStatusEnum.OK.elementCode());
		itrRepository.save(itr);
		return true;
	}

	
}
