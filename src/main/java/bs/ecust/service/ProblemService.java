package bs.ecust.service;

import cs.ecust.domain.view.TmpPrblm;

public interface ProblemService {
	public void createProblem(TmpPrblm pl);
	public String[][] getProblemVls(Long problemId);

}
