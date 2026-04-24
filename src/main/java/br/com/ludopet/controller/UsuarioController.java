package br.com.ludopet.controller;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String home() {
        return "redirect:/cadastro";
    }

    @GetMapping("/cadastro")
    public String mostrarCadastro(Model model) {

        model.addAttribute("usuario", new Usuario());

        return "cadastro_user";
    }

    @GetMapping("/clinicas")
    public String listarClinicas(Model model) {

        model.addAttribute(
                "clinicas",
                usuarioService.listarClinicas()
        );

        return "clinicas";
    }
    @GetMapping("/hospitais")
    public String listarHospitais(Model model) {

        model.addAttribute(
                "hospitais",
                usuarioService.listarHospitais()
        );

        return "hospitais";
    }
}