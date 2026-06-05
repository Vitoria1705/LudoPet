package br.com.ludopet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    // Exibe o formulário (templates/quiz.html)
    @GetMapping("/quiz")
    public String mostrarQuiz() {
        return "quiz";
    }

    // Recebe as respostas, processa e devolve o resultado
    @PostMapping("/resultado-quiz")
    public String resultadoQuiz(@RequestParam("espaco") String espaco,
                                @RequestParam("tempo") String tempo,
                                Model model) {

        String pet = recomendarPet(espaco, tempo);
        model.addAttribute("pet", pet);
        model.addAttribute("descricao", descricao(pet));
        return "resultado-quiz";
    }

    private String recomendarPet(String espaco, String tempo) {
        boolean apartamento = "pequeno".equalsIgnoreCase(espaco);
        boolean poucoTempo  = "pouco".equalsIgnoreCase(tempo);

        if (apartamento && poucoTempo)   return "Peixe ou Hamster 🐹";
        if (apartamento && !poucoTempo)  return "Gato 🐱";
        if (!apartamento && poucoTempo)  return "Cachorro de porte pequeno 🐕";
        return "Cachorro de porte grande 🐶";
    }

    private String descricao(String pet) {
        if (pet.startsWith("Peixe")) return "Pouco espaço e pouco tempo: um pet de baixa manutenção é o ideal.";
        if (pet.startsWith("Gato"))  return "Apartamento combina com gatos: independentes, mas adoram carinho.";
        if (pet.contains("pequeno")) return "Tem espaço, mas pouco tempo: um cão pequeno se adapta bem.";
        return "Casa grande e bastante tempo: um cão grande vai ser muito feliz com você!";
    }
}