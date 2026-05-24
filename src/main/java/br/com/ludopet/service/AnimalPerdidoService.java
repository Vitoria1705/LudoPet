package br.com.ludopet.service;

import br.com.ludopet.model.AnimalPerdido;
import br.com.ludopet.repository.AnimalPerdidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalPerdidoService {

    private final AnimalPerdidoRepository animalPerdidoRepository;

    public AnimalPerdidoService(AnimalPerdidoRepository animalPerdidoRepository) {
        this.animalPerdidoRepository = animalPerdidoRepository;
    }

    public AnimalPerdido salvar(AnimalPerdido animal) {

        if (animal.getNome() == null || animal.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do animal é obrigatório."
            );
        }

        if (animal.getCidade() == null || animal.getCidade().isBlank()) {
            throw new IllegalArgumentException(
                    "A cidade é obrigatória para localizar o animal."
            );
        }

        if (animal.getTelefone() == null || animal.getTelefone().isBlank()) {
            throw new IllegalArgumentException(
                    "Telefone para contato é obrigatório."
            );
        }

        if (animal.getStatus() == null || animal.getStatus().isBlank()) {
            animal.setStatus("perdido");
        }

        if (animal.getDataCadastro() == null) {
            animal.setDataCadastro(LocalDate.now());
        }

        return animalPerdidoRepository.save(animal);
    }

    public List<AnimalPerdido> listarTodos() {
        return animalPerdidoRepository.findAll();
    }

    public List<AnimalPerdido> listarPerdidos() {
        return animalPerdidoRepository.findByStatus("perdido");
    }

    public List<AnimalPerdido> listarEncontrados() {
        return animalPerdidoRepository.findByStatus("encontrado");
    }

    public Optional<AnimalPerdido> buscarPorId(Long id) {
        return animalPerdidoRepository.findById(id);
    }

    public AnimalPerdido marcarComoEncontrado(Long id) {

        AnimalPerdido animal = animalPerdidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Animal perdido não encontrado."
                ));

        animal.setStatus("encontrado");

        return animalPerdidoRepository.save(animal);
    }

    public void deletar(Long id) {
        animalPerdidoRepository.deleteById(id);
    }
}
