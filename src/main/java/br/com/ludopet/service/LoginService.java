package br.com.ludopet.service;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario =
                usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {

            if (usuario.get().getSenha()
                    .equals(senha)) {

                return usuario.get();
            }

        }

        return null;
    }

}