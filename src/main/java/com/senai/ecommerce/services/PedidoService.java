package com.senai.ecommerce.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.senai.ecommerce.dto.ItemPedidoDTO;
import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.entities.Produto;
import com.senai.ecommerce.entities.StatusDoPedido;
import com.senai.ecommerce.entities.Usuario;
import com.senai.ecommerce.repositories.ItemPedidoRepository;
import com.senai.ecommerce.repositories.PedidoRepository;
import com.senai.ecommerce.repositories.ProdutoRepository;
import com.senai.ecommerce.repositories.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll() {
		List<Pedido> pedidos = pedidoRepository.findAllWithItems();
		return pedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
	}

	public Page<PedidoDTO> findAll(Pageable pageable) {
		Page<Pedido> result = pedidoRepository.findAll(pageable);
		return result.map(PedidoDTO::new);
	}

	@Transactional
	public PedidoDTO insert(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setMomento(Instant.now());
		pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
		
		// Busca o cliente pelo ID
		Usuario cliente = usuarioRepository.findById(dto.getClienteId())
			.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		pedido.setCliente(cliente);
		
		pedido = pedidoRepository.save(pedido);

		for (ItemPedidoDTO itemDto : dto.getItems()) {
			Produto produto = produtoRepository.findById(itemDto.getProdutoId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
			
			ItemDoPedido item = new ItemDoPedido(pedido, produto, itemDto.getQuantidade(), produto.getPreco());
			itemPedidoRepository.save(item);
		}

		return new PedidoDTO(pedidoRepository.findPedidoComItens(pedido.getId()));
	}

	@Transactional
	public PedidoDTO update(Long id, PedidoDTO dto) {
		Pedido pedido = pedidoRepository.findPedidoComItens(id);
		if (pedido == null) {
			throw new RuntimeException("Pedido não encontrado");
		}
		copyDtoToEntity(dto, pedido);
		pedido = pedidoRepository.save(pedido);
		return new PedidoDTO(pedidoRepository.findPedidoComItens(pedido.getId()));
	}

	@Transactional
	public void delete(Long id) {
		pedidoRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public PedidoDTO findById(Long id) {
		Pedido pedido = pedidoRepository.findPedidoComItens(id);
		if (pedido == null) {
			throw new RuntimeException("Pedido não encontrado");
		}
		return new PedidoDTO(pedido);
	}

	@Transactional(readOnly = true)
	public List<PedidoDTO> findByCliente(Long clienteId) {
		// Verifica se o cliente existe
		Usuario cliente = usuarioRepository.findById(clienteId)
			.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
			
		// Busca os pedidos do cliente com seus itens
		List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
		if (pedidos.isEmpty()) {
			return new ArrayList<>();
		}
		
		// Busca os pedidos com seus itens
		List<Pedido> pedidosComItens = pedidoRepository.findPedidosWithItems(pedidos);
		return pedidosComItens.stream()
			.map(PedidoDTO::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ItemPedidoDTO> findItensPedido(Long pedidoId) {
		List<ItemDoPedido> itens = itemPedidoRepository.findByPedidoId(pedidoId);
		return itens.stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
	}

	private void copyDtoToEntity(PedidoDTO dto, Pedido entity) {
		if (dto.getStatus() != null) {
			// Verifica se a mudança de status é válida
			boolean mudancaValida = false;
			
			// Fluxo principal: AGUARDANDO_PAGAMENTO -> PAGO -> ENVIADO -> ENTREGUE
			if (entity.getStatus() == StatusDoPedido.AGUARDANDO_PAGAMENTO && 
				(dto.getStatus() == StatusDoPedido.PAGO || dto.getStatus() == StatusDoPedido.CANCELADO)) {
				mudancaValida = true;
			} else if (entity.getStatus() == StatusDoPedido.PAGO && 
				dto.getStatus() == StatusDoPedido.ENVIADO) {
				mudancaValida = true;
			} else if (entity.getStatus() == StatusDoPedido.ENVIADO && 
				dto.getStatus() == StatusDoPedido.ENTREGUE) {
				mudancaValida = true;
			}
			
			if (mudancaValida) {
				entity.setStatus(dto.getStatus());
			} else {
				throw new RuntimeException(
					"Não é possível alterar o status do pedido. " +
					"O fluxo permitido é: AGUARDANDO_PAGAMENTO -> PAGO -> ENVIADO -> ENTREGUE, " +
					"ou AGUARDANDO_PAGAMENTO -> CANCELADO"
				);
			}
		}
		
		if (dto.getItems() != null && !dto.getItems().isEmpty()) {
			entity.getItems().clear();
			for (ItemPedidoDTO itemDto : dto.getItems()) {
				Produto produto = produtoRepository.findById(itemDto.getProdutoId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
				
				ItemDoPedido item = new ItemDoPedido(entity, produto, itemDto.getQuantidade(), produto.getPreco());
				entity.getItems().add(item);
			}
		}
	}
}
