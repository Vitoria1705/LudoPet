package br.com.ludopet.repository;

import br.com.ludopet.model.SolicitacaoParceria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoParceriaRepository extends JpaRepository<SolicitacaoParceria, Long> {

    // busca todas por status (ex: "PENDENTE", "APROVADO")
    List<SolicitacaoParceria> findByStatusOrderByCriadoEmDesc(String status);

    // verifica se email já foi cadastrado
    boolean existsByEmail(String email);
}