package br.com.ultracodeultracodejpa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.dto.ProductDTO;
import br.com.ultracodeultracodejpa.resources.utils.URL;
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
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	ResponseEntity<Page<ProductDTO>> findProductName(
			@RequestParam(value="productToSearch", defaultValue="") String productToSearch, 
			@RequestParam(value="listCategory", defaultValue="") String listCategory, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="24") Integer size, 
			@RequestParam(value="direction", defaultValue="ASC") String direction, 
			@RequestParam(value="properties", defaultValue="name") String properties){
		
		//Converting list id's received from String to Integer
		List<Integer> listCategoryInt =  URL.decodeListInt(listCategory);
		
		//Decoding URL
		String productToSearchDecooded = URL.urlDecode(productToSearch);
		Page<Product> pageObj = productService.search(productToSearchDecooded, listCategoryInt, page, size, direction, properties);
		
		//Converting product to productDTO
		Page<ProductDTO> pageObjDTO = pageObj.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(pageObjDTO);
	}
	
}
