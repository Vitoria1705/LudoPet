package br.com.ludopet.service;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioRepository repository;

    public LoginService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public String autenticar(String email, String senha) {

        Usuario usuario =
                repository.findByEmail(email);

        if (usuario != null &&
                usuario.getSenha().equals(senha)) {

            return "Login realizado";
        }

        return "Email ou senha inválidos";
    }
}