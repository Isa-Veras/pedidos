package com.example.pedidos.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "item")
@SequenceGenerator(name = "item_id_item_seq", sequenceName = "item_id_item_seq", allocationSize = 1)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_item_seq")
    @Column(name = "id_item")
    private Long idItem;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco")
    private Float preco;

    @Column(name = "nome")
    @JsonProperty("produto")
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_pedido", updatable = false)
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "id_cliente", updatable = false)
    private Cliente cliente;


    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
