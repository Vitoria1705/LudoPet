package br.com.ludopet.controller;

import br.com.ludopet.Security.JwtUtil;
import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UsuarioRepository repository;

    public AuthController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario user) {

        Usuario usuario =
                repository.findByEmail(user.getEmail());

        if (usuario != null &&
                usuario.getSenha().equals(user.getSenha())) {

            return JwtUtil.generateToken(usuario.getEmail());
        }

        return "Email ou senha inválidos";
    }
}