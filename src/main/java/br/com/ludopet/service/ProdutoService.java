package br.com.ludopet.service;

import br.com.ludopet.model.Produto;
import br.com.ludopet.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    public List<Produto> listar() {
        return repository.findAll();
    }
}