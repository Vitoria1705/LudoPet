package br.com.ludopet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/quem-somos")
    public String quemSomos() {

        return "quem_somos";
    }

}