package br.com.ultracodeultracodejpa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracodeultracodejpa.domain.Client;
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
	
}
