package com.example.pedidos.repository;

import com.example.pedidos.domain.Item;
import com.example.pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByPedido(Pedido idPedido);

}
