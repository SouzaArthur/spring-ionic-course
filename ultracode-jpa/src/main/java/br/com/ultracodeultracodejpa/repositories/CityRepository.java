package br.com.ultracodeultracodejpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracodeultracodejpa.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
