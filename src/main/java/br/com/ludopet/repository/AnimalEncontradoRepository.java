package br.com.ludopet.repository;

import br.com.ludopet.model.AnimalEncontrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de AnimalEncontrado (camada de ACESSO AO BANCO DE DADOS).
 *
 * Aqui está a "mágica" do Spring Data JPA: NÃO escrevemos nenhuma linha de SQL.
 * Basta esta interface estender JpaRepository<Entidade, TipoDoId> e o Spring
 * gera automaticamente, em tempo de execução, os métodos prontos:
 *
 *   - save(animal)      -> INSERT (novo) ou UPDATE (existente)
 *   - findById(id)      -> SELECT por id
 *   - findAll()         -> SELECT de todos
 *   - deleteById(id)    -> DELETE por id
 *
 * Os métodos abaixo são "Query Methods": o Spring lê o NOME do método e
 * monta o SQL sozinho. Ex.: findByEspecie -> "WHERE especie = ?".
 */
@Repository
public interface AnimalEncontradoRepository extends JpaRepository<AnimalEncontrado, Long> {

    /** Busca todos os animais de uma espécie (ex.: só "Cachorro"). */
    List<AnimalEncontrado> findByEspecie(String especie);

    /**
     * Busca por parte do local, ignorando maiúsculas/minúsculas.
     * Containing = LIKE %...%  | IgnoreCase = não diferencia caixa.
     */
    List<AnimalEncontrado> findByLocalEncontradoContainingIgnoreCase(String local);
}