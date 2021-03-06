package br.com.ultracodeultracodejpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracodeultracodejpa.domain.Address;
import br.com.ultracodeultracodejpa.domain.City;
import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.domain.enums.ClientTypeEnum;
import br.com.ultracodeultracodejpa.domain.enums.RolesEnum;
import br.com.ultracodeultracodejpa.dto.ClientDTO;
import br.com.ultracodeultracodejpa.dto.ClientNewDTO;
import br.com.ultracodeultracodejpa.repositories.AddressRepository;
import br.com.ultracodeultracodejpa.repositories.ClientRepository;
import br.com.ultracodeultracodejpa.security.UserSS;
import br.com.ultracodeultracodejpa.services.exception.AuthorizationException;
import br.com.ultracodeultracodejpa.services.exception.DataIntegrityViolation;
import br.com.ultracodeultracodejpa.services.exception.EmailAlreadyExists;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

//	Client, their phones and addresses
	
	@Autowired
	ClientRepository repo;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;  
	
	public Client getClient(Integer id) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(RolesEnum.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Client> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("No object found with provided id."));
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
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, objDto.getPassword());
	}
	
	public Client update(Client obj) {
		Optional<Client> newObj = repo.findById(obj.getId());
		//Verifying if email already exists
		Client clientWithTheGivenEmail = repo.findByEmail(obj.getEmail());
		if(clientWithTheGivenEmail != null && clientWithTheGivenEmail.getId() != newObj.get().getId()) {
			throw new EmailAlreadyExists("The given e-mail already exists");
		}
		newObj.get().setName(obj.getName());
		newObj.get().setEmail(obj.getEmail());
		
		//Encrypting password
		newObj.get().setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
		
		return repo.save(newObj.get());
	}
	
	public List<Client> findAll(){
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer size, String direction, String properties){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), properties);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDto(ClientNewDTO objDto) {
		//Creating client object
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), 
				ClientTypeEnum.toEnum(objDto.getClientType()), objDto.getPassword());
		//Getting the corresponding city
		City city = new City(objDto.getCityId(), null, null);
		//Creating Address object
		Address addr = new Address(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, city);
		
		cli.getAddresses().add(addr);
		cli.getTelephones().add(objDto.getPhone());
		
		if(objDto.getPhone2() != null) {
			cli.getTelephones().add(objDto.getPhone2());
		}
		
		if(objDto.getPhone3() != null) {
			cli.getTelephones().add(objDto.getPhone3());
		}
		
		return cli;	
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		
		//Verifying if email already exists
		Client clientWithTheGivenEmail = repo.findByEmail(obj.getEmail());
		if(clientWithTheGivenEmail != null) {
			throw new EmailAlreadyExists("The given e-mail already exists");
		}
		
		//Encrypting password
		obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
		
		//Saving Client
		repo.save(obj);
		//Saving Address
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	} 
}
