package br.com.ludopet.controller;

import br.com.ludopet.model.Produto;
import br.com.ludopet.service.ImagemUploadService;
import br.com.ludopet.service.ProdutoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private final ImagemUploadService imagemUploadService;

    public ProdutoController(ProdutoService service, ImagemUploadService imagemUploadService) {
        this.service = service;
        this.imagemUploadService = imagemUploadService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Produto salvarComImagem(
            @RequestParam String nome,
            @RequestParam(required = false) String descricao,
            @RequestParam Double preco,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem) throws IOException {

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);

        String imagemUrl = imagemUploadService.salvarImagemProduto(imagem);
        produto.setImagemUrl(imagemUrl);

        return service.salvar(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return service.salvar(produto);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Produto atualizarComImagem(
            @PathVariable Long id,
            @RequestParam String nome,
            @RequestParam(required = false) String descricao,
            @RequestParam Double preco,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem) throws IOException {

        Produto produto = service.buscarPorId(id).orElseGet(Produto::new);
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);

        String imagemUrl = imagemUploadService.salvarImagemProduto(imagem);
        if (imagemUrl != null) {
            produto.setImagemUrl(imagemUrl);
        }

        return service.salvar(produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
