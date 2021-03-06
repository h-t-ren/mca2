package bs.ecust;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class MCAUserDetails implements UserDetails {
	
	private long userId;
	private String username;
	private String password;
	private String email;
	private boolean enabled;
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private Collection<GrantedAuthority> authorities;
	
	public MCAUserDetails(){}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities=authorities;
	}
	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	private static final long serialVersionUID = 5639683223516504866L;



	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}


}