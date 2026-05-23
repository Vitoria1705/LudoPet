package br.com.ludopet.service;

import br.com.ludopet.model.PetPerdido;
import br.com.ludopet.repository.PetPerdidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetPerdidoService {

    private final PetPerdidoRepository petPerdidoRepository;

    public PetPerdidoService(PetPerdidoRepository petPerdidoRepository) {
        this.petPerdidoRepository = petPerdidoRepository;
    }

    public PetPerdido salvar(PetPerdido pet) {

        if (pet.getNome() == null || pet.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o nome do pet."
            );
        }

        if (pet.getLocal() == null || pet.getLocal().isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o local onde o pet foi visto pela última vez."
            );
        }

        if (pet.getIdade() != null && pet.getIdade() < 0) {
            throw new IllegalArgumentException(
                    "A idade não pode ser negativa."
            );
        }

        return petPerdidoRepository.save(pet);
    }

    public List<PetPerdido> listar() {
        return petPerdidoRepository.findAll();
    }

    public Optional<PetPerdido> buscarPorId(Long id) {
        return petPerdidoRepository.findById(id);
    }

    public PetPerdido atualizar(Long id, PetPerdido dados) {

        PetPerdido pet = petPerdidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pet não encontrado."
                ));

        if (dados.getNome() != null)   pet.setNome(dados.getNome());
        if (dados.getRaca() != null)   pet.setRaca(dados.getRaca());
        if (dados.getIdade() != null)  pet.setIdade(dados.getIdade());
        if (dados.getCor() != null)    pet.setCor(dados.getCor());
        if (dados.getLocal() != null)  pet.setLocal(dados.getLocal());
        if (dados.getImagem() != null) pet.setImagem(dados.getImagem());

        return petPerdidoRepository.save(pet);
    }

    public void deletar(Long id) {
        petPerdidoRepository.deleteById(id);
    }
}
