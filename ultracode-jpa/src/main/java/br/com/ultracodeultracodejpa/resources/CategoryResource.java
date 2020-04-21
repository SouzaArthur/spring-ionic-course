package br.com.ultracodeultracodejpa.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracodeultracodejpa.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@RequestMapping(method=RequestMethod.GET, value="/list")
	public List<Category> Listar() {
		
		Category cat1 = new Category(1, "Informática");
		Category cat2 = new Category(2, "Escritório");
		
		List<Category> catList = new ArrayList<>();
		
		catList.add(cat1);
		catList.add(cat2);
		
		return catList;
		
		
	}
}
