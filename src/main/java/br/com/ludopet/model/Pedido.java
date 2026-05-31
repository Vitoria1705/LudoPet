package br.com.ludopet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;

    private LocalDateTime dataPedido;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "pedido_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produtos_id")
    )
    private List<Produto> produtos;

    @PrePersist
    public void prePersist() {
        this.dataPedido = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }

    public LocalDateTime getDataPedido() { return dataPedido; }
}