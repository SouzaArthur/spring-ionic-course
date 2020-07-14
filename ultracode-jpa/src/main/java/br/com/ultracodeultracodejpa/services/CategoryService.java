package br.com.ultracodeultracodejpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Category;
import br.com.ultracodeultracodejpa.dto.CategoryDTO;
import br.com.ultracodeultracodejpa.repositories.CategoryRepository;
import br.com.ultracodeultracodejpa.services.exception.DataIntegrityViolation;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category getCategory(Integer id) {
		Optional<Category> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado"));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		Category newObj = this.getCategory(obj.getId());
		newObj.setName(obj.getName());
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		this.getCategory(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolation("It's not possible to delete an Entity that depends on another");
		}
		
	}
	
	public List<Category> findAll(){
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer size, String direction, String properties){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), properties);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDto(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
}
