package br.com.ultracodeultracodejpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracodeultracodejpa.domain.Category;
import br.com.ultracodeultracodejpa.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE :productToSearch% AND cat IN :listCategory")
	public Page<Product> search(@Param("productToSearch") String productToSearch, @Param("listCategory") List<Category> listCategory, Pageable pageObj);
}
