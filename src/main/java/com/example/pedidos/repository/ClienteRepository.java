package com.example.pedidos.repository;

import com.example.pedidos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByIdCliente(Long idCliente);
    Cliente findByDocumento(Long documento);
    Cliente save(Cliente cliente);

}
