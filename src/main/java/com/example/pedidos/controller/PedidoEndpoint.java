package com.example.pedidos.controller;

import com.example.pedidos.data.PedidoVO;
import com.example.pedidos.exception.ClienteInvalidoException;
import com.example.pedidos.exception.PedidoInvalidoException;
import com.example.pedidos.service.PedidoService;
import com.example.pedidos.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pedido")
public class PedidoEndpoint {
    @Autowired
    PedidoService pedidoService;

    @Autowired
    RabbitMQService rabbitMQService;


    @GetMapping("todos")
    public ResponseEntity<?> getAllPedidos(){
        String result = pedidoService.findAllPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> getPedidosByCliente(@PathVariable Long id) throws ClienteInvalidoException {
        String result = pedidoService.getPedidosByCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("todos/quantidade-pedido")
    public ResponseEntity<?> getAllQuantidadePedidos() throws ClienteInvalidoException {
        return pedidoService.findAllPedidosQuantidade(null);
    }

    @GetMapping("cliente/quantidade-pedido/{id}")
    public ResponseEntity<?> getQuantidadePedidosByCliente(@PathVariable Long id) throws ClienteInvalidoException {
        return pedidoService.findAllPedidosQuantidade(id);

    }

    @GetMapping("valor/{id}")
    public ResponseEntity<?> getValorPedidoById(@PathVariable Long id) throws PedidoInvalidoException {
        return pedidoService.getValorPedido(id);
    }

    @PostMapping("novo-pedido")
    @ResponseBody
    public ResponseEntity<?> adicionarNovoPedido(@RequestBody PedidoVO pedido) throws ClienteInvalidoException {
        return rabbitMQService.sendToRabbitMQ(pedido);
    }


}
