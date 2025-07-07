package com.example.pedidos.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "cliente_id_cliente_seq", sequenceName = "cliente_id_cliente_seq", allocationSize = 1)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_cliente_seq")
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "documento")
    private Long documento;


    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
