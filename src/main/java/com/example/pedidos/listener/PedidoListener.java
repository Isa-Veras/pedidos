package com.example.pedidos.listener;

import com.example.pedidos.config.RabbitConfig;
import com.example.pedidos.data.PedidoVO;
import com.example.pedidos.domain.Cliente;
import com.example.pedidos.domain.Item;
import com.example.pedidos.domain.Pedido;
import com.example.pedidos.repository.ClienteRepository;
import com.example.pedidos.repository.ItemRepository;
import com.example.pedidos.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE_NOVO_PEDIDO_QUEUE)
    public void receberPedido(PedidoVO pedidoVO) {

        Cliente cliente = clienteRepository.findByIdCliente(pedidoVO.getCodigoCliente());
        Pedido pedido = pedidoRepository.findByIdPedido(pedidoVO.getIdPedido());
        pedido.setStatus(Pedido.STATUS_PROCESSING);
        pedidoRepository.save(pedido);

        for(Item item : pedidoVO.getItens()){
            item.setCliente(cliente);
            item.setPedido(pedido);
        }

        itemRepository.saveAll(pedidoVO.getItens());

        pedido.setStatus(Pedido.STATUS_PROCESSED);
        pedidoRepository.save(pedido);


    }
}
