package br.com.ultracodeultracodejpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracodeultracodejpa.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
