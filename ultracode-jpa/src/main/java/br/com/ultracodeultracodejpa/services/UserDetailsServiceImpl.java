package br.com.ultracodeultracodejpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.repositories.ClientRepository;
import br.com.ultracodeultracodejpa.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Client client = clientRepository.findByEmail(email);
		
		if(client == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getRoles());
	}

}
