package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Itr;


public interface ItrRepository  extends JpaRepository<Itr, Long> {
	
	@Query("select i from Itr i where i.anlz.idAnlz=?1")
	public List<Itr> findByAnalysisId(Long analysisId);
	
	
	@Query("select i from Itr i where i.anlz.idAnlz=?1 and i.itr is null")
	public Itr findRootItrByAnalysisId(Long analysisId);

}
