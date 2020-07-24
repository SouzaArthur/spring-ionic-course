package br.com.ultracodeultracodejpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.repositories.ProductRepository;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product findById(Integer id){
		Optional<Product> obj = productRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("No object found with provided id."));
	}
}
