package br.com.ludopet.controller;

import br.com.ludopet.model.Animal;
import br.com.ludopet.model.Adocao;
import br.com.ludopet.repository.AnimalRepository;
import br.com.ludopet.repository.AdocaoRepository;
import java.util.List;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
//MVC: ANIMAL.JAVA
//Controller: Responsável por receber as requisições do usuário, processar os dados e retornar a resposta adequada.
//VIEW: HTML (ADOCÃO.HTML, DETALHES.HTML, FORMULARIO-ADOÇÃO.HTML)
//Polimorfismo: Capacidade de um objeto se comportar de diferentes formas, dependendo do contexto em que é utilizado.
@Controller  //HERANÇA: Quando uma classe herda características de outra
public class AnimalController {  //HERANÇA: public class

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private AdocaoRepository adocaoRepository;

    // LISTAR ANIMAIS
    @GetMapping("/adocao") //ENCAPSULAMENTO: Proteção dos dados da classe
    public String listarAnimais(Model model) { //ENCAPSULAMENTO:public String

        List<Animal> animais =
                repository
                        .findByStatus("disponivel");

        model.addAttribute(
                "animais",
                animais
        );

        return "adocao";
    }

    //  VER DETALHES
    @GetMapping("/pet/{id}")
    public String detalhesPet(@PathVariable Long id,
                              Model model) {

        Animal animal = repository.findById(id)
                .orElseThrow();

        model.addAttribute("animal", animal);

        return "detalhes";
    }

    @GetMapping("/detalhes/{id}")
    public String verDetalhes(
            @PathVariable Long id,
            Model model) {

        Animal animal =
                repository
                        .findById(id)
                        .orElse(null);

        if (animal == null) {
            return "redirect:/adocao";
        }

        model.addAttribute(
                "animal",
                animal
        );

        return "detalhes";
    }

    @GetMapping("/detalhes/nome/{nome}")
    public String verDetalhesPorNome(
            @PathVariable String nome,
            Model model) {

        Animal animal =
                repository
                        .findFirstByNomeIgnoreCase(nome)
                        .orElse(null);

        if (animal == null) {
            return "redirect:/adocao";
        }

        model.addAttribute(
                "animal",
                animal
        );

        return "detalhes";
    }

    // 🐶 ABRIR FORMULÁRIO
    @GetMapping("/form-adocao/{id}")
    public String mostrarFormulario(
            @PathVariable Long id,
            Model model) {

        Animal animal =
                repository
                        .findById(id)
                        .orElse(null);

        if (animal == null) {
            return "redirect:/adocao";
        }

        model.addAttribute(
                "animal",
                animal
        );

        return "formulario-adocao";
    }

    // 🐶 SALVAR ADOÇÃO (POST)
    @PostMapping("/form-adocao")
    public String salvarAdocao(
            @RequestParam Long idAnimal,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String telefone,
            @RequestParam String motivo,
            Model model) {

        Adocao adocao = new Adocao();

        adocao.setIdAnimal(idAnimal);
        adocao.setNomeAdotante(nome);
        adocao.setEmailAdotante(email);
        adocao.setTelefoneAdotante(telefone);
        adocao.setMotivo(motivo);

        adocaoRepository.save(adocao);

        model.addAttribute(
                "mensagem",
                "Pedido enviado com sucesso! 🐶"
        );


        return "sucesso_adocao";
    }
    @GetMapping("/adocao/todos")
    public String listarTodosAnimais(Model model) {

        List<Animal> animais =
                repository.findAll();

        model.addAttribute(
                "animais",
                animais
        );

        return "adocao";
    }
    @GetMapping("/cadastro-animal")
    public String abrirCadastro(Model model) {

        model.addAttribute("animal", new Animal());

        return "cadastro-animal";
    }
    @PostMapping("/cadastro-animal")
    public String salvarAnimal(
            @ModelAttribute Animal animal,
            @RequestParam("arquivo") MultipartFile arquivo) {

        try {

            if (!arquivo.isEmpty()) {

                String nomeArquivo = System.currentTimeMillis()
                        + "_"
                        + arquivo.getOriginalFilename();

                Path caminho = Paths.get("uploads");

                if (!Files.exists(caminho)) {
                    Files.createDirectories(caminho);
                }

                Files.copy(
                        arquivo.getInputStream(),
                        caminho.resolve(nomeArquivo),
                        StandardCopyOption.REPLACE_EXISTING
                );

                animal.setFoto(nomeArquivo);
            }

            repository.save(animal);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/adocao";
    }

}

