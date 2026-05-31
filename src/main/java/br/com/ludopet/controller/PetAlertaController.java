package br.com.ludopet.controller;

import br.com.ludopet.repository.AnimalPerdidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetAlertaController {

    @Autowired
    private AnimalPerdidoRepository repository;

    @GetMapping("/petalerta")
    public String listarPets(Model model) {

        model.addAttribute(
                "pets",
                repository.findByStatus("perdido")
        );

        return "petalerta";
    }

}