package bs.ecust.controller;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import bs.ecust.SecurityUtils;
import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Anlz;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Instnc;
import bs.ecust.domain.db.Prblm;
import bs.ecust.repository.AnlzRepository;
import bs.ecust.repository.AtrbtRepository;
import bs.ecust.repository.CrtrnRepository;
import bs.ecust.repository.InstncRepository;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.service.AnalysisService;
import bs.ecust.service.InstanceService;
import bs.ecust.service.ProblemService;
import cs.ecust.domain.view.TmpAnlz;
import cs.ecust.domain.view.TmpInstance;



/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月4日 下午9:38:36
 *
 */
@Controller
@Transactional(readOnly=true)
public class AnlzController {
	
	private static final Log logger = LogFactory.getLog(AnlzController.class);
	
	@Autowired private PrblmRepository prblmRepository;
	@Autowired private InstncRepository instncRepository;
	@Autowired private AnlzRepository anlzRepository;
	@Autowired private AnalysisService analysisService;

	@RequestMapping("/analyses")
	public String analyses(
			@RequestParam(value = "problemId") Long problemId,
			@RequestParam(value = "instanceId") Long instanceId,
			Map<String, Object> model) {

		model.put("problem", prblmRepository.findOne(problemId));
		model.put("instance", instncRepository.findOne(instanceId));
		List<Anlz> analyses =anlzRepository.findByInstanceIdAndPblsh(instanceId, StatusEnum.COMMITTED.getStatusCode());
		model.put("analyses", analyses);
		return "analyses";
	}
	@RequestMapping("/analysisForm")
	public String analysisForm(
			@RequestParam(value = "problemId") Long problemId,
			@RequestParam(value = "instanceId") Long instanceId,
			Map<String, Object> model) {

		model.put("problem", prblmRepository.findOne(problemId));
		model.put("instance", instncRepository.findOne(instanceId));
		TmpAnlz tmpAnlz = new TmpAnlz();
		tmpAnlz.setProblemId(problemId);
		tmpAnlz.setInstanceId(instanceId);
		model.put("tmpAnlz", tmpAnlz);
		return "analysisForm";
	}
	
	@Transactional(readOnly = false)
	@RequestMapping(value="/saveAnalysis",method=RequestMethod.POST)
	public String saveAnlysis(@ModelAttribute("tmpAnlz") TmpAnlz tmpAnlz,
			Map<String, Object> model) {
		analysisService.saveTmpAnalysis(tmpAnlz);
		return "redirect:/analyses?problemId="+tmpAnlz.getProblemId()+"&instanceId="+tmpAnlz.getInstanceId();
	}
	
	@RequestMapping("/analysisDelete")
	@Transactional(readOnly = false)
	public String anlysisDelete(
			@RequestParam(value = "analysisId") Long analysisId,
			Map<String, Object> model) {
			Anlz anlz = anlzRepository.findOne(analysisId);
			anlz.setPblsh(StatusEnum.DELETETED.getStatusCode());
			anlzRepository.save(anlz);
			Long problemId = anlz.getInstnc().getPrblm().getIdPrblm();
			Long instanceId = anlz.getInstnc().getIdInstnc();
			return "redirect:/analyses?problemId="+problemId+"&instanceId="+instanceId;
	}

}
