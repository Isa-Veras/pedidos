package com.example.pedidos.service;

import com.example.pedidos.domain.Cliente;
import com.example.pedidos.domain.Item;
import com.example.pedidos.domain.Pedido;
import com.example.pedidos.exception.ClienteInvalidoException;
import com.example.pedidos.exception.PedidoInvalidoException;
import com.example.pedidos.repository.ClienteRepository;
import com.example.pedidos.repository.ItemRepository;
import com.example.pedidos.repository.PedidoRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    private Exception Exception;

    public String findAllPedidos(){
        List<Pedido> pedidos =  pedidoRepository.findAll();
        Map<Cliente, List<Pedido>> pedidosPorCliente = pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getIdCliente));
        String logPedidos= "";
        for (Map.Entry<Cliente, List<Pedido>> entry : pedidosPorCliente.entrySet()) {
            logPedidos = printPedidoCliente(entry.getKey(), logPedidos,entry.getValue());
            }

        return logPedidos;
    }

    public ResponseEntity<?> findAllPedidosQuantidade( Long id ) throws ClienteInvalidoException {
        List<Pedido> pedidos;
        String response;

        if (id == null ){
            pedidos = pedidoRepository.findAll();
            response = "Quatidade de pedido : " + pedidos.size();
        }else{
            Cliente cliente = clienteRepository.findByIdCliente(id);
            if(cliente == null) {
                throw new ClienteInvalidoException();
            }
            pedidos = pedidoRepository.findByCliente(cliente);
            response = "Quatidade de pedido do Cliente " + cliente.getIdCliente() + ": " + pedidos.size();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public ResponseEntity<?> getValorPedido(Long idPedido) throws PedidoInvalidoException {
        Pedido pedido = pedidoRepository.findByIdPedido(idPedido);
        String response;
        if(pedido == null) {
            throw new PedidoInvalidoException();
        }
        List<Item> itens = itemRepository.findByPedido(pedido);
        Float somaValor = 0F;
        for (Item item : itens) {
            somaValor += item.getPreco() * item.getQuantidade();
        }
        response = "Valor do pedido " + pedido.getIdPedido() + "--> R$" + somaValor  ;

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    public String getPedidosByCliente(Long id) throws ClienteInvalidoException {
        Cliente cliente = clienteRepository.findByIdCliente(id);
        if(cliente == null){

            throw new ClienteInvalidoException();
        }
        List<Pedido> pedidos =  pedidoRepository.findByCliente(cliente);
        return printPedidoCliente(cliente,"",pedidos);
    }

    private String printPedidoCliente(Cliente cliente, String preveousString, List <Pedido> pedidos){
        preveousString += "\n\n\n----Cliente: "+ cliente.getIdCliente()+"----";
        preveousString += "\nNome: "+ cliente.getNome();
        preveousString += "\nDocumento: "+ cliente.getDocumento();
        for (Pedido pedido : pedidos) {
            List<Item> itens = itemRepository.findByPedido(pedido);
            preveousString += "\n\nNumero do Pedido: " + pedido.getIdPedido();
            preveousString += "\nData realizada: " + pedido.getData();
            preveousString += "\nQuantidade | Itens do Pedido | Preço";
            for (Item item : itens) {
                int i = 0;
                preveousString += "\n    " + item.getQuantidade() + "       " + item.getNome() + " ----> R$" + item.getPreco() ;
            }
        }
        return preveousString;
    }


}
