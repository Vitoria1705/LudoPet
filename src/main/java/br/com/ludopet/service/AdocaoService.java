package br.com.ludopet.service;

import br.com.ludopet.model.Adocao;
import br.com.ludopet.model.Animal;
import br.com.ludopet.repository.AdocaoRepository;
import br.com.ludopet.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;

    public AdocaoService(AdocaoRepository adocaoRepository,
                         AnimalRepository animalRepository) {
        this.adocaoRepository = adocaoRepository;
        this.animalRepository = animalRepository;
    }

    public Adocao salvar(Adocao adocao) {

        if (adocao.getIdAnimal() == null) {
            throw new IllegalArgumentException(
                    "É necessário informar o animal para a adoção."
            );
        }

        if (adocao.getNomeAdotante() == null
                || adocao.getNomeAdotante().isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do adotante é obrigatório."
            );
        }

        if (adocao.getEmailAdotante() == null
                || !adocao.getEmailAdotante().contains("@")) {
            throw new IllegalArgumentException(
                    "Email do adotante inválido."
            );
        }

        Optional<Animal> animal =
                animalRepository.findById(adocao.getIdAnimal());

        if (animal.isEmpty()) {
            throw new IllegalArgumentException(
                    "Animal não encontrado."
            );
        }

        Animal a = animal.get();

        if ("adotado".equalsIgnoreCase(a.getStatus())) {
            throw new IllegalStateException(
                    "Este animal já foi adotado."
            );
        }

        return adocaoRepository.save(adocao);
    }

    public List<Adocao> listar() {
        return adocaoRepository.findAll();
    }

    public Optional<Adocao> buscarPorId(Long id) {
        return adocaoRepository.findById(id);
    }

    public void deletar(Long id) {
        adocaoRepository.deleteById(id);
    }

    public Animal confirmarAdocao(Long idAdocao) {

        Adocao adocao = adocaoRepository
                .findById(idAdocao)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Adoção não encontrada."
                ));

        Animal animal = animalRepository
                .findById(adocao.getIdAnimal())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Animal vinculado à adoção não encontrado."
                ));

        animal.setStatus("adotado");

        return animalRepository.save(animal);
    }
}
