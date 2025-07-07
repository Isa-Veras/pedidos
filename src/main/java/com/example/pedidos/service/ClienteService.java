package com.example.pedidos.service;

import com.example.pedidos.domain.Cliente;
import com.example.pedidos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public ResponseEntity<?> addNovoCliente(Cliente novoCliente){
        try {
            Cliente cliente = clienteRepository.findByDocumento(novoCliente.getDocumento());
            if (cliente != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Documento usado para o cliente "+cliente.getNome()+", Código do cliente " + cliente.getIdCliente());
            }
            novoCliente = clienteRepository.save(novoCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente criado com sucesso, seu codigo é: " + novoCliente.getIdCliente());
        } catch (Exception e) {
            throw new RuntimeException("Algum campo nao foi preenchicido corretamente");
        }
    }
}
