package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Itr;
import bs.ecust.domain.db.ItrEntVl;


public interface ItrEntVlRepository  extends JpaRepository<ItrEntVl, Long> {
	
   @Query("select e from ItrEntVl e where e.spcEnt.idEnt=?1 and e.itr.idItr =?2")
	public ItrEntVl findByEntIdAndItrId(Long entId, Long itrId);
}
