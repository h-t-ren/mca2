package bs.ecust.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Anlz;

public interface AnlzRepository  extends JpaRepository<Anlz, Long> {

	@Query("select a from Anlz a where a.instnc.idInstnc=?1 and a.pblsh=?2")
	public List<Anlz> findByInstanceIdAndPblsh(Long instanceId,Long status);

}
