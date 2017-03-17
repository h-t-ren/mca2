package bs.ecust;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import bs.ecust.domain.db.Prblm;
import bs.ecust.repository.PrblmRepository;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
//@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PrblmRepositoryTests {


	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PrblmRepository repository;

	@Test
	public void findBySnmShouldReturnNull() throws Exception {
		Prblm testPrblm = new Prblm();
		testPrblm.setSnm("shortName");
		this.entityManager.persist(testPrblm);
		Prblm prblm = this.repository.findBySnm("shortName");
		assertThat(prblm).isNotNull();
	}

}
