package br.com.ludopet.service;

import br.com.ludopet.model.Animal;
import br.com.ludopet.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal salvar(Animal animal) {

        if (animal.getNome() == null || animal.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do animal é obrigatório."
            );
        }

        if (animal.getStatus() == null || animal.getStatus().isBlank()) {
            animal.setStatus("disponivel");
        }

        return animalRepository.save(animal);
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public List<Animal> listarDisponiveis() {
        return animalRepository.findByStatus("disponivel");
    }

    public List<Animal> listarAdotados() {
        return animalRepository.findByStatus("adotado");
    }

    public Optional<Animal> buscarPorId(Long id) {
        return animalRepository.findById(id);
    }

    public Animal atualizar(Long id, Animal dados) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Animal não encontrado."
                ));

        if (dados.getNome() != null)      animal.setNome(dados.getNome());
        if (dados.getIdade() != null)     animal.setIdade(dados.getIdade());
        if (dados.getRaca() != null)      animal.setRaca(dados.getRaca());
        if (dados.getSexo() != null)      animal.setSexo(dados.getSexo());
        if (dados.getPorte() != null)     animal.setPorte(dados.getPorte());
        if (dados.getDescricao() != null) animal.setDescricao(dados.getDescricao());
        if (dados.getFoto() != null)      animal.setFoto(dados.getFoto());
        if (dados.getStatus() != null)    animal.setStatus(dados.getStatus());

        return animalRepository.save(animal);
    }

    public Animal marcarComoAdotado(Long id) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Animal não encontrado."
                ));

        animal.setStatus("adotado");

        return animalRepository.save(animal);
    }

    public void deletar(Long id) {
        animalRepository.deleteById(id);
    }
}
