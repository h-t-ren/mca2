/**
 * 
 */
package bs.ecust;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月5日 下午12:55:36
 *
 */
@Configuration
@EnableWebSecurity
public class MyApplicationSecurity extends WebSecurityConfigurerAdapter {
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/bootstrap-dist/**","/css/**","/font-awesome/**","/frontend_theme/**","/img/**","/js/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
		http.authorizeRequests().antMatchers("/api/factorvalues/**").permitAll();
		//.hasIpAddress("192.168.0/24");
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
		.fullyAuthenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/", true)
	    .failureUrl("/login?error").and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
		.exceptionHandling().accessDeniedPage("/access?error");
		http.httpBasic();
		//http.rememberMe().key("SpringSecurityAclDemo-rmkey-BUUttZnBJCa#U=4Dwg@%5_ptCC8wHtlY").userDetailsService(jdbcUserService);

	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
		auth
			.userDetailsService(userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	

}