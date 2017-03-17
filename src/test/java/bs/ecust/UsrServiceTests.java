package bs.ecust;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.service.UsrService;

import org.springframework.transaction.annotation.Transactional;

//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {McaApplication.class,DatabaseConfig.class})
public class UsrServiceTests {
//
//	@Autowired
//	private UsrService usrService;
//
//	@Test
//	public void registTest() {
//		Usr hongtao = new Usr();
//		hongtao.setLogin("marek");
//		hongtao.setPassword("marek");
//		hongtao.setIsDisabled(0l);
//		//System.out.println(hongtao.getLogin());
//		usrService.register(hongtao);
//	}

}
