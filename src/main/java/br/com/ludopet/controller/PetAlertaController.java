package br.com.ludopet.controller;

import br.com.ludopet.model.AnimalPerdido;
import br.com.ludopet.repository.AnimalPerdidoRepository;
import br.com.ludopet.service.VisionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class PetAlertaController {

    private final AnimalPerdidoRepository repository;
    private final VisionService visionService;

    public PetAlertaController(AnimalPerdidoRepository repository,
                               VisionService visionService) {
        this.repository = repository;
        this.visionService = visionService;
    }

    @GetMapping("/petalerta")
    public String petAlerta(Model model) {
        model.addAttribute("animais", repository.findByStatus("perdido"));
        return "petalerta";
    }

    @PostMapping("/pet-alerta/buscar")
    public String buscar(@RequestParam("foto") MultipartFile foto,
                         Model model) {

        List<String> labels = visionService.analisarImagem(foto);
        String resposta = String.join(" ", labels).toLowerCase();

        System.out.println("LABELS DETECTADAS: " + labels);
        System.out.println("RESPOSTA PROCESSADA: " + resposta);

        // =============================
        // 🔍 DETECTAR ESPÉCIE
        // =============================
        String especie = null;

        if (resposta.contains("cat") || resposta.contains("kitten") ||
                resposta.contains("tabby") || resposta.contains("gato")) {
            especie = "Gato";
        } else if (resposta.contains("dog") || resposta.contains("puppy") ||
                resposta.contains("hound") || resposta.contains("terrier") ||
                resposta.contains("retriever") || resposta.contains("labrador") ||
                resposta.contains("poodle") || resposta.contains("bulldog") ||
                resposta.contains("canine") || resposta.contains("pet")) {
            especie = "Cachorro";
        }

        // 🔥 FALLBACK (IMPORTANTE)
        if (especie == null) {
            especie = "Cachorro";
        }

        // =============================
        // 🔍 DETECTAR RAÇA
        // =============================
        String raca = null;

        Map<String, String> racas = new LinkedHashMap<>();
        racas.put("labrador", "Labrador");
        racas.put("poodle", "Poodle");
        racas.put("bulldog", "Bulldog");
        racas.put("golden retriever", "Golden Retriever");
        racas.put("husky", "Husky");
        racas.put("beagle", "Beagle");
        racas.put("shih tzu", "Shih Tzu");
        racas.put("chihuahua", "Chihuahua");
        racas.put("tabby", "Gato Rajado");
        racas.put("siamese", "Siamês");
        racas.put("persian", "Persa");

        for (Map.Entry<String, String> entry : racas.entrySet()) {
            if (resposta.contains(entry.getKey())) {
                raca = entry.getValue();
                break;
            }
        }

        // =============================
        // 🔍 DETECTAR COR
        // =============================
        String cor = null;

        Map<String, String> cores = new LinkedHashMap<>();
        cores.put("black", "Preto");
        cores.put("white", "Branco");
        cores.put("brown", "Marrom");
        cores.put("yellow", "Amarelo");
        cores.put("gray", "Cinza");
        cores.put("grey", "Cinza");
        cores.put("orange", "Laranja");
        cores.put("cream", "Creme");

        for (Map.Entry<String, String> entry : cores.entrySet()) {
            if (resposta.contains(entry.getKey())) {
                cor = entry.getValue();
                break;
            }
        }

        // =============================
        // 🔍 BUSCA NO BANCO (INTELIGENTE)
        // =============================
        List<AnimalPerdido> resultados = new ArrayList<>();
        List<AnimalPerdido> todos = repository.findByStatus("perdido");

        for (AnimalPerdido a : todos) {

            int score = 0;

            // compara raça
            if (raca != null && a.getRaca() != null &&
                    a.getRaca().toLowerCase().contains(raca.toLowerCase())) {
                score++;
            }

            // compara cor
            if (cor != null && a.getCor() != null &&
                    a.getCor().toLowerCase().contains(cor.toLowerCase())) {
                score++;
            }

            // compara espécie
            if (especie != null && a.getEspecie() != null &&
                    a.getEspecie().toLowerCase().contains(especie.toLowerCase())) {
                score++;
            }

            // 🔥 COMPARA DESCRIÇÃO (JEITO CERTO)
            if (a.getDescricao() != null) {
                String desc = a.getDescricao().toLowerCase();

                if (resposta.contains(desc)) {
                    score += 2; // peso maior
                }
            }

            //  SE BATEU PELO MENOS 1 COISA → ACEITA
            if (score >= 2) {
                resultados.add(a);
            } else if (score == 1 && raca != null) {
                // só aceita 1 ponto se for raça (mais confiável)
                resultados.add(a);
            }
        }

        // =============================
        //  RETORNO PRA TELA
        // =============================
        model.addAttribute("resultados", resultados);
        model.addAttribute("racaDetectada", raca);
        model.addAttribute("corDetectada", cor);
        model.addAttribute("especieDetectada", especie);
        model.addAttribute("animais", repository.findByStatus("perdido"));

        return "petalerta";
    }
}