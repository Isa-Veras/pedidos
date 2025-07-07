package com.example.pedidos.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pedido")
@SequenceGenerator(name = "pedido_id_pedido_seq", sequenceName = "pedido_id_pedido_seq", allocationSize = 1)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_id_pedido_seq")
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente", updatable = false)
    private Cliente cliente;

    @Column(name = "data")
    private Date data;

    @Column(name = "status")
    private String status;

    @Column(name = "code")
    private Long code;

    public static String STATUS_NEW = "new";
    public static String STATUS_PROCESSING = "processing";
    public static String STATUS_PROCESSED = "processed";

    public Pedido(Cliente cliente, Date data, String status, Long code) {
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.code = code;
    }

    public Pedido() {

    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getIdCliente() {
        return cliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.cliente = idCliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
