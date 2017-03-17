package bs.ecust;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import bs.ecust.domain.db.Usr;
import bs.ecust.repository.UsrRepository;

@Service
public class SecurityUtils {
	@Autowired
	private  UsrRepository usrRepository;
	
	public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) {
            return ((MCAUserDetails) auth.getPrincipal()).getUsername();
        } else {
            return auth.getPrincipal().toString();
        }
    }
	public Usr getCurrentDBUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userid;
        if (auth.getPrincipal() instanceof UserDetails) {
        	userid =((MCAUserDetails) auth.getPrincipal()).getUserId();
        } else {
        	userid = null;
        }
        
       return usrRepository.findOne(userid);
    }
	public MCAUserDetails getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((MCAUserDetails) auth.getPrincipal());
        } else {
            return null;
        }
    }


    
}