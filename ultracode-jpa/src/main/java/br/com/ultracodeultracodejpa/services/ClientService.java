package br.com.ultracodeultracodejpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.dto.ClientDTO;
import br.com.ultracodeultracodejpa.repositories.ClientRepository;
import br.com.ultracodeultracodejpa.services.exception.DataIntegrityViolation;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

//	Client, their phones and addresses
	
	@Autowired
	ClientRepository repo;
	
	public Client getClient(Integer id) {
		Optional<Client> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("No object finded with the provided id."));
	}
	
	public void delete(Integer id) {
		repo.findById(id);
		try{
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolation("It's not possible to delete an Entity that depends on another");
		}
	}
	
	public Client fromDto(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client update(Client obj) {
		Optional<Client> newObj = repo.findById(obj.getId());
		newObj.get().setName(obj.getName());
		newObj.get().setEmail(obj.getEmail());
		return repo.save(newObj.get());
	}
	
	public List<Client> findAll(){
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer size, String direction, String properties){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), properties);
		return repo.findAll(pageRequest);
	}
}
