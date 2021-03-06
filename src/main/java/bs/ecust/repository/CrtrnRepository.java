package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Crtrn;


public interface CrtrnRepository  extends JpaRepository<Crtrn, Long> {
	
	@Query("select c from Crtrn c where c.instnc.idInstnc=?1 order by c.seq asc")
	public List<Crtrn> findByInstanceId(Long instanceId);
}
