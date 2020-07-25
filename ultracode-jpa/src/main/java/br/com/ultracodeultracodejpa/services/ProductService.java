package br.com.ultracodeultracodejpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Category;
import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.repositories.CategoryRepository;
import br.com.ultracodeultracodejpa.repositories.ProductRepository;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Product findById(Integer id){
		Optional<Product> obj = productRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("No object found with provided id."));
	}
	
	public Page<Product> search(String productToSearch, List<Integer> listCategoyIds, Integer page, Integer size, String direction, String properties){
		PageRequest pageRequestObj = PageRequest.of(page, size, Direction.valueOf(direction), properties);
		
		//Search for categories with received id's
		List<Category> categoryList = categoryRepository.findAllById(listCategoyIds);
		
		return productRepository.search(productToSearch, categoryList, pageRequestObj);
	}
}
