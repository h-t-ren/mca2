package bs.ecust.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bs.ecust.SecurityUtils;
import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Instnc;
import bs.ecust.domain.db.Prblm;
import bs.ecust.repository.AtrbtRepository;
import bs.ecust.repository.CrtrnRepository;
import bs.ecust.repository.InstncRepository;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.service.FileMeta;
import bs.ecust.service.FileUploadService;
import bs.ecust.service.InstanceService;
import bs.ecust.service.ProblemService;
import bs.ecust.service.parser.McpParserException;
import bs.ecust.service.parser.ProblemLoader;
import cs.ecust.domain.view.TmpInstance;
import cs.ecust.domain.view.TmpPrblm;



/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月4日 下午9:38:36
 *
 */
@Controller
@Transactional(readOnly = true)
public class InstanceController {
	
	private static final Log logger = LogFactory.getLog(InstanceController.class);
	
	@Autowired private PrblmRepository prblmRepository;
	@Autowired
	private CrtrnRepository crtrnRepository;
	
	@Autowired
	private InstncRepository instncRepository;
	
	@Autowired private ProblemService problemService;
	@Autowired private SecurityUtils securityUtils;

	@Autowired
	private AtrbtRepository atrbtRepository;
	@Autowired private InstanceService instanceService;

	@RequestMapping("/instances")
	public String instances(
			@RequestParam(value = "problemId") Long problemId,
			Map<String, Object> model) {
		List<Instnc> instances =instncRepository.findByProblemIdAndPblsh(problemId,StatusEnum.COMMITTED.getStatusCode());
		Prblm problem = prblmRepository.findOne(problemId);
		model.put("problem", problem);
		model.put("instances", instances);
		return "instances";
	}
	
	
	@RequestMapping("/instanceForm")
	public String instanceForm(
			@RequestParam(value = "problemId") Long problemId,
			Map<String, Object> model) {
		List<Atrbt> atrbts = atrbtRepository.findByProblemId(problemId);
		Prblm problem = prblmRepository.findOne(problemId);
		model.put("problem", problem);
		model.put("attributes", atrbts);
		TmpInstance tmpInstance = new TmpInstance();
		tmpInstance.setProblemId(problemId);
		model.put("tmpInstance", tmpInstance);
		return "instanceForm";
	}
	
	@RequestMapping("/instanceView")
	public String instanceView(
			@RequestParam(value = "problemId") Long problemId,
			@RequestParam(value = "instanceId") Long instanceId,
			Map<String, Object> model) {
		Instnc instnc = instncRepository.findOne(instanceId);
		model.put("problem", instnc.getPrblm());
		model.put("instance", instnc);
		model.put("criteria", crtrnRepository.findByInstanceId(instanceId));
		return "instanceView";
	}
	
	@Transactional(readOnly = false)
	@RequestMapping(value="/saveInstance",method=RequestMethod.POST)
	public String saveInstance(@ModelAttribute("tmpInstance") TmpInstance tmpInstance,
			Map<String, Object> model) {
		instanceService.saveTmpInstance(tmpInstance);
		return "redirect:/instances?problemId="+tmpInstance.getProblemId();
	}
	
	@RequestMapping("/instanceDelete")
	@Transactional(readOnly = false)
	public String instanceDelete(
			@RequestParam(value = "instanceId") Long instanceId,
			Map<String, Object> model) {
			Instnc instnc = instncRepository.findOne(instanceId);
			instnc.setPblsh(StatusEnum.DELETETED.getStatusCode());
			instncRepository.save(instnc);
			return "redirect:/instances?problemId="+instnc.getPrblm().getIdPrblm();
	}

}
