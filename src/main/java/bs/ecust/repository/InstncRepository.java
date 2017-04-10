package bs.ecust.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bs.ecust.domain.db.Instnc;

public interface InstncRepository  extends JpaRepository<Instnc, Long> {

	@Query("select i from Instnc i where i.prblm.idPrblm=?1 and i.pblsh=?2")
	public List<Instnc> findByProblemIdAndPblsh(Long problemId,Long status);

}
