package bs.ecust;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.ecust.domain.db.Usr;
import bs.ecust.repository.UsrRepository;


@Service("userDetailService")
public class MCAUserDetailService implements Serializable,
		UserDetailsService {
	private static final long serialVersionUID = 1L;
	@Autowired private UsrRepository usrRepository;
	//@Autowired private SecurityUtils securityUtils;
	@Transactional(readOnly=true)

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Usr user = usrRepository.findByLogin(username);
		if (user == null) {
			
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		MCAUserDetails userDetails = new MCAUserDetails();
		userDetails.setUsername(user.getLogin());
		userDetails.setEmail("111@111.com");
		userDetails.setEnabled(true);
	    userDetails.setUserId(user.getIdUsr());
		userDetails.setAuthorities(null);
		userDetails.setPassword(user.getPassword());
		return userDetails;
	}
	

}