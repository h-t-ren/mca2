package bs.ecust.service;

import bs.ecust.domain.db.Usr;

public interface UsrService {
	public boolean register(Usr usr);
	public boolean isRegisted(String username);

}
