package br.com.ludopet.controller;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;
import br.com.ludopet.service.LoginService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {

        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            Model model) {

        String resultado =
                loginService.autenticar(email, senha);

        if(resultado.equals("Login realizado")) {

            Usuario usuario =
                    usuarioRepository.findByEmail(email);

            session.setAttribute(
                    "usuarioLogado",
                    usuario
            );

            return "redirect:/dashboard";
        }

        model.addAttribute(
                "erro",
                "Email ou senha inválidos"
        );

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}