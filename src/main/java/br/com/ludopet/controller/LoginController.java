package br.com.ludopet.controller;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String mostrarLogin() {

        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @RequestParam String email,
            @RequestParam String senha,
            Model model) {

        String resultado =
                loginService.autenticar(email, senha);

        if(resultado.equals("Login realizado")) {

            return "dashboard";
        }

        model.addAttribute(
                "erro",
                "Email ou senha inválidos"
        );

        return "login";
    }

}