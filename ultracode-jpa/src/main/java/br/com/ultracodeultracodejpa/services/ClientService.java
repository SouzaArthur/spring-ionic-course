package br.com.ultracodeultracodejpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.repositories.ClientRepository;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

//	Client, their phones and addresses
	
	@Autowired
	ClientRepository repo;
	
	public Client getClient(Integer id) {
		Optional<Client> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado"));
	}
}
