package com.example.pedidos.repository;

import com.example.pedidos.domain.Cliente;
import com.example.pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAll();
    Pedido findByIdPedido(Long idPedido);
    Pedido findByCode(Long code);
    List<Pedido> findByCliente(Cliente cliente);
    Pedido save(Pedido pedido);

}
