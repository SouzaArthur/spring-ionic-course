package br.com.ultracodeultracodejpa.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.dto.ClientDTO;
import br.com.ultracodeultracodejpa.dto.ClientNewDTO;
import br.com.ultracodeultracodejpa.services.ClientService;

@RestController
@RequestMapping(value="/client")
public class ClientResource {
	
	@Autowired
	ClientService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Client obj = service.getClient(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> detele(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id){
		Client obj = service.fromDto(objDto);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> listObj = service.findAll();
		List<ClientDTO> listDTO = listObj.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value="/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page, 
			@RequestParam(value="size", defaultValue="24")Integer size, 
			@RequestParam(value="direction", defaultValue="ASC")String direction, 
			@RequestParam(value="properties", defaultValue="name") String properties){
		
		Page<Client> obj = service.findPage(page, size, direction, properties);
		Page<ClientDTO> newObj = obj.map(clientObj -> new ClientDTO(clientObj));
		
		return ResponseEntity.ok().body(newObj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDto){
		//Transform clientNewDTO to Client and Address
		Client obj = service.fromDto(objDto);
		//Saving client and address 
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
}
