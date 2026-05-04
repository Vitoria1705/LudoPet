package br.com.ludopet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LojaController {

    @GetMapping("/kit-boas-vindas")
    public String kitBoasVindas() {
        return "kit_boas_vindas";
    }

    @GetMapping("/lojapet")
    public String lojaPet() {
        return "lojapet";
    }
}