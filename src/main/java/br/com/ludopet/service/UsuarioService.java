package br.com.ludopet.service;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {

        usuario.setDataCadastro(LocalDate.now());

        return usuarioRepository.save(usuario);
    }

    public boolean emailExiste(String email) {

        return usuarioRepository.findByEmail(email).isPresent();

    }

    public List<Usuario> listarClinicas() {

        return usuarioRepository.findByTipo("clinica");

    }

    public List<Usuario> listarHospitais() {

        return usuarioRepository.findByTipo("hospital");

    }

}