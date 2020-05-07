package Nagraj.PagesSecurity.Role.PermisionSecurityBySB.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Nagraj.PagesSecurity.Role.PermisionSecurityBySB.Model.User;

@SuppressWarnings("serial")
public class Userprinciple implements UserDetails {

	private User user;
	
	public Userprinciple(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return Collections.singleton(new SimpleGrantedAuthority("USER"));
		 List<GrantedAuthority> authorities = new ArrayList<>();

	        // Extract list of permissions (name)
	        this.user.getPermissionList().forEach(p -> {
	            GrantedAuthority authority = new SimpleGrantedAuthority(p);
	            authorities.add(authority);
	        });

	        // Extract list of roles (ROLE_name)
	        this.user.getRoleList().forEach(r -> {
	            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
	            authorities.add(authority);
	        });

	        return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.getActive()==1;
	}

}
