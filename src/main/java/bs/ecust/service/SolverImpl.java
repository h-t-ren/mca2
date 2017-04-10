package bs.ecust.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import bs.ecust.controller.ProblemController;


@Service("solver")
public class SolverImpl implements Solver {
	private static final Log logger = LogFactory.getLog(ProblemController.class);
	
	@Value("${solver.path}")
	private String solverPath;
	@Value("${solver.version}")
	private String solverVersion;
	@Value("${solver.schema}")
	private String solverSchema;
	
	public  boolean Call(long itr) throws Exception {
		String command = solverPath + solverVersion + " -itr_id " + itr + " -schema " + solverSchema;
		logger.debug("call solver: " + command);
		Runtime r = Runtime.getRuntime();
		Process p = r.exec(command);
		p.waitFor();
		int returnCode =p.exitValue();
		if (returnCode == 0)
		{
			logger.debug("call solver successfully");
			return true;
		}

		else
		{
			logger.error("sth wrong with calling solver return code is: " + p.exitValue());
			return false;
		}
			

	}

	
}
