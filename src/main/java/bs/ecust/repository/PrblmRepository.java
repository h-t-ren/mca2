package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;


public interface PrblmRepository  extends JpaRepository<Prblm, Long> {
	
	public Prblm findBySnm(String snm);
	
	public List<Prblm> findByUsrAndPblsh(Usr usr,Long status);

}
