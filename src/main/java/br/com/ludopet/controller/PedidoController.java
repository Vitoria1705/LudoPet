package br.com.ludopet.controller;

import br.com.ludopet.model.Pedido;
import br.com.ludopet.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Pedido finalizar(@RequestBody Pedido pedido) {
        return repository.save(pedido);
    }
}