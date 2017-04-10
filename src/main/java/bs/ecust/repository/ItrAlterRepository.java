package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Altrntv;
import bs.ecust.domain.db.ItrAlter;
import bs.ecust.domain.db.ItrAlterId;
import bs.ecust.domain.db.ItrDots;
import bs.ecust.domain.db.ItrDotsId;



public interface ItrAlterRepository  extends JpaRepository<ItrAlter, ItrAlterId> {

	@Query("select i from ItrAlter i where i.id.idItr =?1 and i.id.idAltrntv=?2 order by i.id.idCrtrn asc")
	public List<ItrAlter> findByItrIdAndAltrntvId(Long itrId,Long altrntvId);

}
