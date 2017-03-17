package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Atrbt;


public interface AtrbtRepository  extends JpaRepository<Atrbt, Long> {
	
	@Query("select a from Atrbt a where a.prblm.idPrblm=?1")
	public List<Atrbt> findByProblemId(Long problemId);

}
