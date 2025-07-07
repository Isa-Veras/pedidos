package com.example.pedidos.service;

import com.example.pedidos.data.PedidoVO;
import com.example.pedidos.domain.Cliente;
import com.example.pedidos.domain.Pedido;
import com.example.pedidos.exception.ClienteInvalidoException;
import com.example.pedidos.repository.ClienteRepository;
import com.example.pedidos.repository.PedidoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void sendToProcess(PedidoVO order) {
        rabbitTemplate.convertAndSend("novo.pedido.queue", order);
    }

    public ResponseEntity<?> sendToRabbitMQ(PedidoVO pedidoVO) throws ClienteInvalidoException {

        Cliente cliente = clienteRepository.findByIdCliente(pedidoVO.getCodigoCliente());
        if(cliente == null ){
            throw new ClienteInvalidoException();

        }
        if (pedidoRepository.findByCode(pedidoVO.getCodigoPedido()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Código de Pedido Existente");

        } else {

            Pedido novoPedido = new Pedido(cliente, Date.from(Instant.now()), Pedido.STATUS_NEW, pedidoVO.getCodigoPedido());
            novoPedido = pedidoRepository.save(novoPedido);
            pedidoVO.setIdPedido(novoPedido.getIdPedido());
            

            sendToProcess(pedidoVO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido Adicionado na Fila de Processamento");
        }
    }
}
