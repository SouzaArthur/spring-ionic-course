package br.com.ultracodeultracodejpa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.dto.ProductDTO;
import br.com.ultracodeultracodejpa.services.ProductService;

@RestController
@RequestMapping(value="/producties")
public class ProductResource {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<ProductDTO> findById(@PathVariable Integer id){
		
		Product productObj = productService.findById(id);
		ProductDTO productDtoObj = new ProductDTO(productObj);
		
		return ResponseEntity.ok().body(productDtoObj);
	}
	
}
