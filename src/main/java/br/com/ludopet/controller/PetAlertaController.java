package br.com.ludopet.controller;

import br.com.ludopet.model.PetPerdido;
import br.com.ludopet.repository.PetPerdidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetAlertaController {

    @Autowired
    private PetPerdidoRepository repository;

    @GetMapping("/petalerta")
    public String listarPets(Model model) {
        model.addAttribute("pets", repository.findAll());
        return "petalerta";
    }

    @GetMapping("/cadastroPetPerdido")
    public String formulario(Model model) {
        model.addAttribute("pet", new PetPerdido());
        return "cadastroPetPerdido";
    }

    @PostMapping("/salvarPet")
    public String salvar(@ModelAttribute PetPerdido pet) {
        repository.save(pet);
        return "redirect:/petalerta";
    }
}