package br.com.ludopet.repository;

import br.com.ludopet.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
//HERANÇA(herda características de outra)

public interface AdocaoRepository
        extends JpaRepository<Adocao, Long> {

}