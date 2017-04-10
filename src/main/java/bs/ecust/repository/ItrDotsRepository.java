package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Altrntv;
import bs.ecust.domain.db.ItrDots;
import bs.ecust.domain.db.ItrDotsId;



public interface ItrDotsRepository  extends JpaRepository<ItrDots, ItrDotsId> {
	

	@Query("select i from ItrDots i where i.id.idItr =?1 and i.id.idCrtrn=?2")
	public List<ItrDots> findByItrIdAndCriterionId(Long itrId,Long criterionId);
	


}
