package br.com.ludopet.repository;

import br.com.ludopet.model.AnimalPerdido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalPerdidoRepository
        extends JpaRepository<AnimalPerdido, Long> {

    List<AnimalPerdido> findByStatus(String status);
    List<AnimalPerdido> findByRacaContainingIgnoreCase(String raca);
    List<AnimalPerdido> findByCorContainingIgnoreCase(String cor);
    List<AnimalPerdido> findByEspecieContainingIgnoreCase(String especie);
}