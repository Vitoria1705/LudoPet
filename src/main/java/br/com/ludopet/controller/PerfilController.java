package br.com.ludopet.controller;

import br.com.ludopet.model.Usuario;
import br.com.ludopet.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/perfil")
    public String perfil(
            HttpSession session,
            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuarioLogado"
                );

        if(usuario == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "usuario",
                usuario
        );

        return "perfil";
    }

    @PostMapping("/perfil/salvar")
    public String salvarPerfil(
            @ModelAttribute Usuario dadosUsuario,
            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuarioLogado"
                );

        usuario.setNome(dadosUsuario.getNome());
        usuario.setTelefone(dadosUsuario.getTelefone());
        usuario.setCidade(dadosUsuario.getCidade());
        usuario.setEstado(dadosUsuario.getEstado());
        usuario.setDescricao(dadosUsuario.getDescricao());

        usuarioRepository.save(usuario);

        session.setAttribute(
                "usuarioLogado",
                usuario
        );

        return "redirect:/perfil";
    }
}