package br.com.ultracodeultracodejpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracodeultracodejpa.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
