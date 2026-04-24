package br.com.ludopet.controller;

import br.com.ludopet.model.FormularioAdocao;
import br.com.ludopet.repository.FormularioAdocaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/adocoes")
public class AdminAdocaoController {

    @Autowired
    private FormularioAdocaoRepository formularioRepository;

    @GetMapping
    public String listarAdocoes(Model model) {

        List<FormularioAdocao> lista =
                formularioRepository.findAll();

        model.addAttribute("adocoes", lista);

        return "admin-adocoes";

    }

}