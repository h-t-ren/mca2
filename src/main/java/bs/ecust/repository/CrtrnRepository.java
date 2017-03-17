package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Crtrn;


public interface CrtrnRepository  extends JpaRepository<Crtrn, Long> {
	

}
