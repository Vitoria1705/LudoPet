package br.com.ludopet.repository;

import br.com.ludopet.model.FormularioAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormularioAdocaoRepository
        extends JpaRepository<FormularioAdocao, Long> {
}