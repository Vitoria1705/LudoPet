package br.com.ludopet.service;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {

        usuario.setDataCadastro(LocalDate.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public boolean emailExiste(String email) {

        return usuarioRepository.findByEmail(email) != null;
    }

    public List<Usuario> listarClinicas() {

        return usuarioRepository.findByTipo("clinica");
    }

    public List<Usuario> listarHospitais() {

        return usuarioRepository.findByTipo("hospital");
    }
}