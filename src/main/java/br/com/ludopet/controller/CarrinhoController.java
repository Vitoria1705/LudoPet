package br.com.ludopet.controller;

import br.com.ludopet.model.Carrinho;
import br.com.ludopet.repository.CarrinhoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    private final CarrinhoRepository repository;

    public CarrinhoController(CarrinhoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Carrinho salvar(@RequestBody Carrinho carrinho) {
        return repository.save(carrinho);
    }
}