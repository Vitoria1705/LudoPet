package br.com.ludopet.controller;

import br.com.ludopet.model.Animal;
import br.com.ludopet.model.FormularioAdocao;
import br.com.ludopet.repository.AnimalRepository;
import br.com.ludopet.repository.FormularioAdocaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/formulario-adocao")
public class FormularioAdocaoController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FormularioAdocaoRepository formularioRepository;

    // 🔹 MOSTRAR FORMULÁRIO
    @GetMapping("/{id}")
    public String mostrarFormulario(@PathVariable Long id, Model model) {

        Optional<Animal> animal = animalRepository.findById(id);

        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "formulario-adocao";
        }

        return "redirect:/adocao";
    }

    // 🔹 SALVAR FORMULÁRIO
    @PostMapping
    public String salvarFormulario(
            @RequestParam Long idAnimal,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String telefone,
            @RequestParam String motivo) {

        FormularioAdocao form = new FormularioAdocao();

        form.setIdAnimal(idAnimal);
        form.setNome(nome);
        form.setEmail(email);
        form.setTelefone(telefone);
        form.setMotivo(motivo);

        formularioRepository.save(form);

        // 👇 AQUI É O FLUXO CORRETO PRA CONFIRMAÇÃO
        return "redirect:/formulario-adocao/confirmacao?idAnimal=" + idAnimal;
    }

    // 🔹 PÁGINA DE CONFIRMAÇÃO
    @GetMapping("/confirmacao")
    public String confirmacao(@RequestParam Long idAnimal, Model model) {

        Optional<Animal> animal = animalRepository.findById(idAnimal);

        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            return "confirmacao-adocao";
        }

        return "redirect:/adocao";
    }
}