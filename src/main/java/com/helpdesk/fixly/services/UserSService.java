package com.helpdesk.fixly.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helpdesk.fixly.models.PessoasModel;
import com.helpdesk.fixly.reposistories.PessoasRepository;
import com.helpdesk.fixly.security.UserSS;

@Service
public class UserSService implements UserDetailsService{

	@Autowired
	private PessoasRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<PessoasModel> userEmail = repo.findByEmail(email);
		
		if(userEmail.isPresent()) {
			return new UserSS(userEmail.get().getId(), userEmail.get().getEmail(), userEmail.get().getSenha(), userEmail.get().getPerfis());
		}
		
		throw new UsernameNotFoundException(email);
	}

}
