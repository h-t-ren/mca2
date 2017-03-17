package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Altrntv;



public interface AltrntvRepository  extends JpaRepository<Altrntv, Long> {
	
	@Query("select a from Altrntv a where a.idPrblm=?1")
	public List<Altrntv> findByProblemId(Long problemId);
	

}
