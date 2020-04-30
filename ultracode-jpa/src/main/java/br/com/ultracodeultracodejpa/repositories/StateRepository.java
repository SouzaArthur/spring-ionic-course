package br.com.ultracodeultracodejpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracodeultracodejpa.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
