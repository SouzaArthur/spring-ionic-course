package br.com.ultracodeultracodejpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Category;
import br.com.ultracodeultracodejpa.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Optional<Category> getCategory(Integer id) {
		Optional<Category> obj = repo.findById(id);
		
		return obj;
	}
	
}
