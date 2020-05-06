package br.com.ultracodeultracodejpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracodeultracodejpa.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
