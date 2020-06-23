package br.com.ultracodeultracodejpa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracodeultracodejpa.domain.Order;
import br.com.ultracodeultracodejpa.services.OrderService;

@RestController
@RequestMapping(value="/order")
public class OrderResource {
	
	@Autowired
	OrderService orderService;

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> findOrder(@PathVariable Integer id){
		Order obj = orderService.getOrder(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}

