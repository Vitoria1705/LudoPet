package br.com.ludopet.service;


import br.com.ludopet.model.AnimalEncontrado;
import br.com.ludopet.repository.AnimalEncontradoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de AnimalEncontrado (camada de REGRAS DE NEGÓCIO).
 *
 * Fica no MEIO do caminho:
 *
 *      Controller  ->  Service  ->  Repository  ->  Banco de Dados
 *     (recebe a       (decide o     (executa o
 *      requisição)     que fazer)    SQL)
 *
 * Por que existir? Para o Controller não falar direto com o banco. Toda a
 * lógica (validações, ajustes, tratamento) mora aqui, num lugar só. Se a
 * regra mudar, muda-se apenas o Service.
 */
@Service
public class AnimalEncontradoService {

    /**
     * Injeção de dependência: o Spring entrega uma instância pronta do
     * repositório pelo construtor. Não precisamos dar "new" manualmente.
     */
    private final AnimalEncontradoRepository repository;

    public AnimalEncontradoService(AnimalEncontradoRepository repository) {
        this.repository = repository;
    }

    /** Retorna a lista completa de animais encontrados. */
    public List<AnimalEncontrado> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca um animal pelo id.
     * Optional pode vir vazio (id inexistente); por isso usamos orElse(null).
     */
    public AnimalEncontrado buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Salva (cadastra OU atualiza) um animal.
     * Regra de negócio simples: se ninguém informou o nome, gravamos
     * "Sem nome", já que muitos animais encontrados não têm nome conhecido.
     */
    public AnimalEncontrado salvar(AnimalEncontrado animal) {
        if (animal.getNome() == null || animal.getNome().isBlank()) {
            animal.setNome("Sem nome");
        }
        return repository.save(animal);
    }

    /** Exclui o registro pelo id (ex.: dono reencontrou o animal). */
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    /** Filtra por espécie usando o Query Method do repositório. */
    public List<AnimalEncontrado> filtrarPorEspecie(String especie) {
        return repository.findByEspecie(especie);
    }
}
