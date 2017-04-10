package bs.ecust;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.servlet.DisplayChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bs.ecust.domain.db.Prblm;
import bs.ecust.domain.db.Usr;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.service.UsrService;
import bs.ecust.service.parser.McpParserException;
import bs.ecust.service.parser.ProblemLoader;
import cs.ecust.domain.view.TmpAltrntv;
import cs.ecust.domain.view.TmpPrblm;



@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class McaApplication {
	

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(McaApplication.class, args);
//		PrblmRepository prblmRepository =ctx.getBean(PrblmRepository.class);
//		for(Prblm p:prblmRepository.findAll() )
//		{
//			System.out.println("x: " + p.getIdPrblm());
//		}
//	UsrService us =	ctx.getBean(UsrService.class);
//	Usr u = new Usr();
//	u.setLogin("hongtao");
//	u.setPassword("hongtao");
//	u.setIsDisabled(0l);
//	us.register(u);
		
		
//		ProblemLoader p = ctx.getBean(ProblemLoader.class);
//		try {
//			TmpPrblm pl = p.parseFile2TmpPrblm("/Users/renhongtao/mcafiles/car8.csv");
//		    System.out.println("problem name: " + pl.getShrtnm() +", description: " + pl.getDscrptn() );
//		    for(TmpAltrntv al : pl.getAltrntvs())
//		    {
//		    	System.out.print("alternative: " + al.getShrtnm() +"    values: ");
//		    	for(Double v: al.getValues())
//		    	{
//		    		System.out.print(v +", ");
//		    	}
//		    	System.out.println();
//		    }
//		} catch (McpParserException e) {
//			e.printStackTrace();
//		}
	
	
	}
	
	@Bean
	public ServletRegistrationBean getDisplayChartServlet(){
		DisplayChart displayChart=new DisplayChart();
		ServletRegistrationBean registrationBean=new ServletRegistrationBean();
		registrationBean.setServlet(displayChart);
		List<String> urlMappings=new ArrayList<String>();
		urlMappings.add("/servlet/DisplayChart");////访问，可以添加多个
		registrationBean.setUrlMappings(urlMappings);
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}
}
