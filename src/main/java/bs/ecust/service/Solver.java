package bs.ecust.service;

import org.springframework.stereotype.Service;

@Service("solver")
public interface Solver {
  public boolean Call(long itr) throws Exception;
}
