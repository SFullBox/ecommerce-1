package com.senai.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.ItemDTO;
import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.repositories.ItemRepository;
import com.senai.ecommerce.repositories.PedidoRepository;

@Service
public class ItemService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemRepository itemRepository;

    public ItemDTO teste(ItemDTO itemDTO) {
        
      

    

        // Criar um novo objeto ItemDoPedido
        ItemDoPedido itemPedido = new ItemDoPedido();
        itemPedido.setPreco(itemPedido.getPreco());
        itemPedido.setQuantidade(itemPedido.getQuantidade());

        // Buscar o Pedido pelo ID contido em itemDTO.getId().getPedido().getId()
        Pedido pedido = pedidoRepository.findById(itemDTO.getId().getPedido().getId()).orElse(null);

        // Verifique se o Pedido foi encontrado
        
            // Associar o Pedido ao ItemDoPedido
            itemPedido.setPedido(pedido);
            // Agora você pode salvar ou manipular o itemPedido conforme necessário
            // itemRepository.save(itemPedido); // Se for necessário salvar o itemPedido no banco de dados
        

        return new ItemDTO(itemPedido);  // Retorna o ItemDTO com o itemPedido
    }
}
