package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bs.ecust.domain.db.AltrntvVl;
import bs.ecust.domain.db.AltrntvVlId;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;


public interface AltrntvVlRepository  extends JpaRepository<AltrntvVl, AltrntvVlId> {
	
	

}
