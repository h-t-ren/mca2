package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bs.ecust.domain.db.AltrntvVl;
import bs.ecust.domain.db.AltrntvVlId;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;


public interface AltrntvVlRepository  extends JpaRepository<AltrntvVl, AltrntvVlId> {
	
	@Query("select a from AltrntvVl a where a.id.idAtrbt=?1 order by a.orgValue asc")
	public List<AltrntvVl> findByAttributeIdOrderByOrgValue(Long attributeId);

}
