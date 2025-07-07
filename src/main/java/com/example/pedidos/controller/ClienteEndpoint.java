package com.example.pedidos.controller;

import com.example.pedidos.domain.Cliente;
import com.example.pedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/cliente")
public class ClienteEndpoint {

    @Autowired
    ClienteService clienteService;

    @PostMapping("novo")
    public ResponseEntity<?> addNovoCliente(@RequestBody Cliente cliente){
        return clienteService.addNovoCliente(cliente);
    }


}
