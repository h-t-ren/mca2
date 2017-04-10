package bs.ecust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Crtrn;
import bs.ecust.domain.db.ItrBoxplot;
import bs.ecust.domain.db.ItrBoxplotId;


public interface ItrBoxplotRepository  extends JpaRepository<ItrBoxplot, ItrBoxplotId> {

}
