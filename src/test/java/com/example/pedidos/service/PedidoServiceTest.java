package com.example.pedidos.service;

import com.example.pedidos.domain.Item;
import com.example.pedidos.domain.Pedido;
import com.example.pedidos.exception.PedidoInvalidoException;
import com.example.pedidos.repository.ItemRepository;
import com.example.pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ItemRepository itemRepository;

    @Test
    void deveRetornarValorDoPedidoComSucesso() throws  PedidoInvalidoException {
        Long idPedido = 1L;

        Pedido pedido = new Pedido();
        pedido.setIdPedido(idPedido);

        List<Item> itens = List.of(
                criarItem(10.0f, 2,"Livro"),
                criarItem(5.0f, 1, "Caderno")
        );

        when(pedidoRepository.findByIdPedido(idPedido)).thenReturn(pedido);
        when(itemRepository.findByPedido(pedido)).thenReturn(itens);

        ResponseEntity<?> response = pedidoService.getValorPedido(idPedido);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Valor do pedido 1--> R$25.0", response.getBody());
    }

    private Item criarItem(Float preco, int quantidade, String nome) {
        Item item = new Item();
        item.setPreco(preco);
        item.setNome(nome);
        item.setQuantidade(quantidade);
        return item;
    }
}
