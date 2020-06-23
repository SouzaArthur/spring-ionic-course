package br.com.ultracodeultracodejpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Order;
import br.com.ultracodeultracodejpa.repositories.OrderRepository;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public Order getOrder(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("No object finded with the provided id."));
	}
}
