package bs.ecust.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bs.ecust.SecurityUtils;
import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Prblm;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.service.FileMeta;
import bs.ecust.service.FileUploadService;
import bs.ecust.service.ProblemService;
import bs.ecust.service.parser.McpParserException;
import bs.ecust.service.parser.ProblemLoader;
import cs.ecust.domain.view.TmpPrblm;



/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月4日 下午9:38:36
 *
 */
@Controller
public class ProblemController {
	
	private static final Log logger = LogFactory.getLog(ProblemController.class);
	
	@Autowired private PrblmRepository prblmRepository;
	@Autowired
	private ProblemLoader problemLoader;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired private ProblemService problemService;
	@Autowired private SecurityUtils securityUtils;
	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		return "redirect:/problems";
	}
	
	
	@RequestMapping("/problems")
	public String problems(Map<String, Object> model) {
		List<Prblm> pList = prblmRepository.findByUsrAndPblsh(securityUtils.getCurrentDBUser(),StatusEnum.COMMITTED.getStatusCode());
		model.put("problems", pList);
		return "problems";
	}
	
	@RequestMapping("/problemUpload")
	public String problemUpload(Map<String, Object> model) {
		 model.put("error", "no");
		return "problemUpload";
	}
	

	@RequestMapping("/problemView")
	public String problemView(
			@RequestParam(value = "problemId", required = false) Long problemId,
			Map<String, Object> model) {
		if(problemId!=null)
		{
			model.put("problemId", problemId);
			Prblm problem = prblmRepository.findOne(problemId);
			model.put("problem", problem);
		}
		return "problemView";
	}
	
	
	@RequestMapping("/problemDelete")
	public String problemDelete(
			@RequestParam(value = "problemId") Long problemId,
			Map<String, Object> model) {
			Prblm problem = prblmRepository.findOne(problemId);
			problem.setPblsh(StatusEnum.DELETETED.getStatusCode());
			prblmRepository.save(problem);
		   return "redirect:/problems";
	}
	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/uploadProblemFile", method = RequestMethod.POST)
	public String uploadProblemFile(Map<String, Object> model, MultipartHttpServletRequest request,
			HttpServletResponse response) {
		FileMeta fileMeta = new FileMeta();
		if (request.getFileNames().hasNext()) {
			fileMeta = fileUploadService.upload(request, response,false);
			try {
				TmpPrblm tmpPrblm=problemLoader.parseFile2TmpPrblm(fileMeta.getFilePath());
				problemService.createProblem(tmpPrblm);
			} catch (McpParserException e) {
			    model.put("error", e.getMessage());
				return "problemUpload";
			}catch (Exception e) {
			    model.put("error", e.getMessage());
				return "problemUpload";
			}

		}
		 model.put("error", "no");
		return "redirect:/problems";
	}
	
	
	
	@Transactional(readOnly = true)
	@RequestMapping(value="/api/getAlternativeVls", method=RequestMethod.GET)
	public @ResponseBody String[][] getAlternativeVls(@RequestParam("problemId") Long problemId)
	{
		return problemService.getProblemVls(problemId);
	}
	
}
