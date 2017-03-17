/**
 * 
 */
package bs.ecust.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月4日 下午9:38:36
 *
 */
@Controller
public class NotImplementedController {
	

	private static final Log logger = LogFactory.getLog(NotImplementedController.class);
	
	@RequestMapping("/notImpl")
	public String notImpl(Map<String, Object> model) {
		return "notImpl";
	}
	

}
