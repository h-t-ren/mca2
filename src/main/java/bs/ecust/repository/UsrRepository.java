package bs.ecust.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;


public interface UsrRepository  extends JpaRepository<Usr, Long> {
	
	public Usr findByLogin(String login);

}
