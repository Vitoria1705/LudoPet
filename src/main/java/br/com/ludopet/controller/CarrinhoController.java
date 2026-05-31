package br.com.ludopet.controller;

import br.com.ludopet.model.Carrinho;
import br.com.ludopet.repository.CarrinhoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin("*")
public class CarrinhoController {

    private final CarrinhoRepository repository;

    public CarrinhoController(CarrinhoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Carrinho salvar(@RequestBody Carrinho carrinho) {
        return repository.save(carrinho);
    }

    @GetMapping
    public List<Carrinho> listar() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/limpar")
    public void limpar() {
        repository.deleteAll();
    }
}