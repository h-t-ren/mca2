package bs.ecust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.ecust.domain.db.Usr;
import bs.ecust.repository.UsrRepository;

@Service("usrService")
@Transactional(readOnly=true)
public class UsrServiceImpl implements UsrService {
	
	@Autowired private UsrRepository usrRepository;
	
	@Override
	@Transactional(readOnly=false)
	public boolean register(Usr usr) {
		String username = usr.getLogin(); 
		if(isRegisted(username))
			return false;  //已经被注册
		else
		{
			String password = usr.getPassword();
			usr.setPassword(new BCryptPasswordEncoder().encode(password));
			usrRepository.save(usr);
			return true;
		}

	}

	@Override
	public boolean isRegisted(String username) {
		Usr usr = usrRepository.findByLogin(username);
		if(usr!=null)
			return true;
		else
			return false;
	}



}
