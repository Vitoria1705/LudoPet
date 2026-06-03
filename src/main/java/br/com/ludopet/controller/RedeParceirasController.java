package br.com.ludopet.controller;

import br.com.ludopet.model.Parceiro;
import br.com.ludopet.model.SolicitacaoParceria;
import br.com.ludopet.repository.ParceiroRepository;
import br.com.ludopet.repository.SolicitacaoParceriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RedeParceirasController {

    private final ParceiroRepository parceiroRepository;
    private final SolicitacaoParceriaRepository solicitacaoRepo;

    public RedeParceirasController(ParceiroRepository parceiroRepository,
                                   SolicitacaoParceriaRepository solicitacaoRepo) {
        this.parceiroRepository = parceiroRepository;
        this.solicitacaoRepo    = solicitacaoRepo;
    }

    // ── Página principal da Rede Parceiras ──────────────────
    @GetMapping("/rede-parceiras")
    public String redeParceiras(Model model) {
        List<Parceiro> parceiros = parceiroRepository.findAll();
        model.addAttribute("parceiros", parceiros);
        return "rede-parceiras";
    }

    // ── Formulário de solicitação ────────────────────────────
    @GetMapping("/quero-patrocinar")
    public String formularioParceria(Model model) {
        model.addAttribute("solicitacao", new SolicitacaoParceria());
        return "quero-patrocinar";
    }

    // ── Salva solicitação no banco ───────────────────────────
    @PostMapping("/quero-patrocinar")
    public String salvarSolicitacao(
            @ModelAttribute SolicitacaoParceria solicitacao,
            RedirectAttributes redirectAttrs) {

        // Impede cadastro duplicado pelo mesmo e-mail
        if (solicitacaoRepo.existsByEmail(solicitacao.getEmail())) {
            redirectAttrs.addFlashAttribute("erro",
                    "Este e-mail já enviou uma solicitação. Entraremos em contato em breve!");
            return "redirect:/quero-patrocinar";
        }

        solicitacaoRepo.save(solicitacao);

        redirectAttrs.addFlashAttribute("sucesso",
                "Solicitação enviada com sucesso! Nossa equipe entrará em contato em até 2 dias úteis.");
        return "redirect:/quero-patrocinar";
    }
}