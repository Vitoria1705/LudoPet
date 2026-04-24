package br.com.ludopet.repository;

import br.com.ludopet.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository
        extends JpaRepository<Animal, Long> {

    List<Animal> findByStatus(String status);

    List<Animal> findAll();

}