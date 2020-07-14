package br.com.ultracodeultracodejpa.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.dto.ClientDTO;
import br.com.ultracodeultracodejpa.services.ClientService;

@RestController
@RequestMapping(value="/client")
public class ClientResource {
	
	@Autowired
	ClientService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Client obj = service.getClient(id);
		ClientDTO newObj = new ClientDTO(obj);
		
		return ResponseEntity.ok().body(newObj);
	}
	
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> listObj = service.findAll();
		List<ClientDTO> listDTO = listObj.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
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
	
}
