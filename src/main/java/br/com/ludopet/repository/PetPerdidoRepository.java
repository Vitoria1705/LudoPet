package br.com.ludopet.repository;

import br.com.ludopet.model.PetPerdido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetPerdidoRepository extends JpaRepository<PetPerdido, Long> {
}