package br.com.ludopet.repository;

import br.com.ludopet.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//‘INTERFACE’: Define um contrato que as classes podem implementar, garantindo que elas forneçam certas funcionalidades.
public interface AnimalRepository //INTERFACE
        extends JpaRepository<Animal, Long> {

    List<Animal> findByStatus(String status);

    Optional<Animal> findFirstByNomeIgnoreCase(String nome);

    List<Animal> findAll();

}
