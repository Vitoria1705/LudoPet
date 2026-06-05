package br.com.ludopet.controller;

import br.com.ludopet.model.AnimalEncontrado;
import br.com.ludopet.service.AnimalEncontradoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller de AnimalEncontrado (camada WEB / porta de entrada).
 *
 * É quem RECEBE as requisições do navegador (URLs) e DECIDE qual tela mostrar.
 * Ele não fala com o banco diretamente: pede tudo ao Service.
 *
 * Fluxo geral:
 *   navegador  ->  Controller  ->  Service  ->  Repository  ->  Banco
 *
 * Cada método abaixo está ligado a uma URL:
 *   GET  = só exibir/buscar dados (abrir uma página)
 *   POST = enviar dados de um formulário (criar/alterar/excluir)
 */
@Controller
public class AnimalEncontradoController {

    private final AnimalEncontradoService service;

    /** O Spring injeta o Service automaticamente pelo construtor. */
    public AnimalEncontradoController(AnimalEncontradoService service) {
        this.service = service;
    }

    /**
     * LISTAR (READ): abre a página com todos os animais encontrados.
     * Coloca a lista no Model com o nome "animais", e o HTML
     * (Animais_Encontrados.html) lê esse atributo para montar os cards.
     */
    @GetMapping("/animais-encontrados")
    public String listar(Model model) {
        model.addAttribute("animais", service.listarTodos());
        // "animalNovo" é um objeto vazio que alimenta o formulário de cadastro.
        model.addAttribute("animalNovo", new AnimalEncontrado());
        return "Animais_Encontrados"; // templates/Animais_Encontrados.html
    }

    /**
     * CADASTRAR (CREATE): recebe os dados do formulário.
     *
     * @ModelAttribute monta sozinho um objeto AnimalEncontrado a partir dos
     * campos do formulário (casa pelo "name" de cada input com o atributo da
     * classe). Depois mandamos salvar e voltamos para a listagem.
     *
     * O "redirect:" recarrega a página via nova URL, evitando recadastro
     * acidental se o usuário apertar F5 (padrão Post/Redirect/Get).
     */
    @PostMapping("/animais-encontrados/salvar")
    public String salvar(@ModelAttribute AnimalEncontrado animal) {
        service.salvar(animal);
        return "redirect:/animais-encontrados";
    }

    /**
     * EXCLUIR (DELETE): remove um registro pelo id vindo na URL.
     * Ex.: quando o dono reencontra o animal, tira-se o anúncio.
     * {id} na URL é capturado pelo @PathVariable.
     */
    @PostMapping("/animais-encontrados/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/animais-encontrados";
    }
}