package br.com.ludopet.repository;

import br.com.ludopet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    Usuario findByEmailAndSenha(String email, String senha);

    List<Usuario> findByTipo(String tipo);
}
