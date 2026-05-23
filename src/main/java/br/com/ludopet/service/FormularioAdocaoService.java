package br.com.ludopet.service;

import br.com.ludopet.model.Animal;
import br.com.ludopet.model.FormularioAdocao;
import br.com.ludopet.repository.AnimalRepository;
import br.com.ludopet.repository.FormularioAdocaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormularioAdocaoService {

    private final FormularioAdocaoRepository formularioRepository;
    private final AnimalRepository animalRepository;

    public FormularioAdocaoService(FormularioAdocaoRepository formularioRepository,
                                   AnimalRepository animalRepository) {
        this.formularioRepository = formularioRepository;
        this.animalRepository = animalRepository;
    }

    public FormularioAdocao salvar(FormularioAdocao formulario) {

        if (formulario.getIdAnimal() == null) {
            throw new IllegalArgumentException(
                    "Informe o animal de interesse."
            );
        }

        if (formulario.getNome() == null || formulario.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "Nome é obrigatório."
            );
        }

        if (formulario.getEmail() == null
                || !formulario.getEmail().contains("@")) {
            throw new IllegalArgumentException(
                    "Email inválido."
            );
        }

        if (formulario.getTelefone() == null
                || formulario.getTelefone().isBlank()) {
            throw new IllegalArgumentException(
                    "Telefone é obrigatório."
            );
        }

        if (formulario.getMotivo() == null
                || formulario.getMotivo().length() < 10) {
            throw new IllegalArgumentException(
                    "Descreva o motivo da adoção com pelo menos 10 caracteres."
            );
        }

        Optional<Animal> animal =
                animalRepository.findById(formulario.getIdAnimal());

        if (animal.isEmpty()) {
            throw new IllegalArgumentException(
                    "Animal não encontrado."
            );
        }

        if ("adotado".equalsIgnoreCase(animal.get().getStatus())) {
            throw new IllegalStateException(
                    "Este animal já foi adotado."
            );
        }

        return formularioRepository.save(formulario);
    }

    public List<FormularioAdocao> listar() {
        return formularioRepository.findAll();
    }

    public Optional<FormularioAdocao> buscarPorId(Long id) {
        return formularioRepository.findById(id);
    }

    public void deletar(Long id) {
        formularioRepository.deleteById(id);
    }
}
