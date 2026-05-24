package br.com.ludopet.controller;

import br.com.ludopet.model.Animal;
import br.com.ludopet.model.Adocao;
import br.com.ludopet.repository.AnimalRepository;
import br.com.ludopet.repository.AdocaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AdocaoRepository adocaoRepository;

    // LISTAR ANIMAIS
    @GetMapping("/adocao")
    public String listarAnimais(Model model) {

        List<Animal> animais =
                animalRepository
                        .findByStatus("disponivel");

        model.addAttribute(
                "animais",
                animais
        );

        return "adocao";
    }

    // 🐶 VER DETALHES
    @GetMapping("/detalhes/{id}")
    public String verDetalhes(
            @PathVariable Long id,
            Model model) {

        Animal animal =
                animalRepository
                        .findById(id)
                        .orElse(null);

        if (animal == null) {
            return "redirect:/adocao";
        }

        model.addAttribute(
                "animal",
                animal
        );

        return "detalhes";
    }

    @GetMapping("/detalhes/nome/{nome}")
    public String verDetalhesPorNome(
            @PathVariable String nome,
            Model model) {

        Animal animal =
                animalRepository
                        .findFirstByNomeIgnoreCase(nome)
                        .orElse(null);

        if (animal == null) {
            return "redirect:/adocao";
        }

        model.addAttribute(
                "animal",
                animal
        );

        return "detalhes";
    }

    // 🐶 ABRIR FORMULÁRIO
    @GetMapping("/form-adocao/{id}")
    public String mostrarFormulario(
            @PathVariable Long id,
            Model model) {

        Animal animal =
                animalRepository
                        .findById(id)
                        .orElse(null);

        if (animal == null) {
            return "redirect:/adocao";
        }

        model.addAttribute(
                "animal",
                animal
        );

        return "form_adocao";
    }

    // 🐶 SALVAR ADOÇÃO (POST)
    @PostMapping("/form-adocao")
    public String salvarAdocao(
            @RequestParam Long idAnimal,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String telefone,
            @RequestParam String motivo,
            Model model) {

        Adocao adocao = new Adocao();

        adocao.setIdAnimal(idAnimal);
        adocao.setNomeAdotante(nome);
        adocao.setEmailAdotante(email);
        adocao.setTelefoneAdotante(telefone);
        adocao.setMotivo(motivo);

        adocaoRepository.save(adocao);

        model.addAttribute(
                "mensagem",
                "Pedido enviado com sucesso! 🐶"
        );


        return "sucesso_adocao";
    }
    @GetMapping("/adocao/todos")
    public String listarTodosAnimais(Model model) {

        List<Animal> animais =
                animalRepository.findAll();

        model.addAttribute(
                "animais",
                animais
        );

        return "adocao";
    }
}
