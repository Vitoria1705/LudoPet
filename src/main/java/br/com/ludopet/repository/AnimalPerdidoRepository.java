package br.com.ludopet.repository;

import br.com.ludopet.model.AnimalPerdido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalPerdidoRepository //INTERFACE Um contrato que define métodos que devem ser usados
        extends JpaRepository<AnimalPerdido, Long> {

    List<AnimalPerdido> findByStatus(String status);//POLIMORFISMO( método pode funcionar de formas diferentes)

}