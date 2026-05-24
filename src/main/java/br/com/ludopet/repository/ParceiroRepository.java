package br.com.ludopet.repository;

import br.com.ludopet.model.Parceiro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository
        extends JpaRepository<Parceiro, Long> {
}