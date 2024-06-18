package it.epicode.phronesis.businesslayer.security;

import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository user;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var userEntity = user.findOneByUsername(username).orElseThrow();
		return SecurityUserDetails.build(userEntity);
	}

}
