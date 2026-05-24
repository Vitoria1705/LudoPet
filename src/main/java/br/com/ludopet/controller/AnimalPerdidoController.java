package br.com.ludopet.controller;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import br.com.ludopet.model.AnimalPerdido;
import br.com.ludopet.repository.AnimalPerdidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AnimalPerdidoController {

    @Autowired
    private AnimalPerdidoRepository repository;

    //  LISTAR ANIMAIS PERDIDOS
    @GetMapping("/animais-perdidos")
    public String listar(Model model) {

        List<AnimalPerdido> lista =
                repository.findByStatus("perdido");

        model.addAttribute(
                "animais",
                lista
        );

        return "animais_perdidos";
    }

    //  ABRIR FORMULÁRIO
    @GetMapping("/novo-animal-perdido")
    public String novoAnimal(Model model) {

        model.addAttribute(
                "animal",
                new AnimalPerdido()
        );

        return "form_animal_perdido";
    }

    //  SALVAR
    @PostMapping("/salvar-animal-perdido")
    public String salvar(
            @ModelAttribute AnimalPerdido animal,
            @RequestParam("arquivo") MultipartFile arquivo) {

        try {

            if (!arquivo.isEmpty()) {

                String nomeArquivo =
                        arquivo.getOriginalFilename();

                String caminho =
                        "src/main/resources/static/uploads/";

                File destino =
                        new File(caminho + nomeArquivo);

                destino.getParentFile().mkdirs();

                arquivo.transferTo(destino);

                animal.setFoto("/uploads/" + nomeArquivo);
            }

            animal.setStatus("perdido");

            animal.setDataCadastro(
                    LocalDate.now()
            );

            repository.save(animal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/animais-perdidos";
    }

}
