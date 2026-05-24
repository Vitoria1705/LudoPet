package br.com.ludopet.controller;

import br.com.ludopet.repository.ParceiroRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedeParceirasController {

    @Autowired
    private ParceiroRepository repository;

    @GetMapping("/rede-parceiras")
    public String redeParceiras(Model model) {

        model.addAttribute(
                "parceiros",
                repository.findAll()
        );

        return "rede-parceiras";
    }
}