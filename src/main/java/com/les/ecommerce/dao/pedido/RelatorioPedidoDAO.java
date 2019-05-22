package com.les.ecommerce.dao.pedido;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.application.venda.Relatorio;
import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.repository.pedido.RelatorioPedidoRepository;

@Component
public class RelatorioPedidoDAO extends AbstractDAO {

	@Autowired
	private RelatorioPedidoRepository repository;
	
	@Override
	public void salvar(IEntidade entidade) {
		
	}

	@Override
	public void alterar(IEntidade entidade) {
		
	}

	@Override
	public List<IEntidade> consultar(IEntidade entidade) {
		Relatorio relatorio = (Relatorio) entidade;
		List<Pedido> pedidos = Collections.emptyList();
		if(relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) {
			pedidos =  repository.findPedidoByDateRelatorio(relatorio);
		}
		
		
		if(relatorio.getDataInicial() == null && relatorio.getDataFinal() == null) {
			pedidos = repository.findAll();
		}
		
		
		if(relatorio.getDepartamento() != null && relatorio.getDepartamento().getNome() != null) {
			pedidos = pedidos.stream()
					.map(p -> {
						p.setItens(p.getItens()
								.stream()
								.filter(item -> item.getProduto()
												.getDepartamento()
												.getNome()
												.equals(relatorio.getDepartamento().getNome()))
												.collect(Collectors.toSet()));
						return p;
					})
					.collect(Collectors.toList());
		}
		
		if(relatorio.getCartao() != null && relatorio.getCartao().getBandeira() != null) {
			pedidos = pedidos.stream().map( p -> {
				p.setCartao(p.getCartao()
						.stream()
						.filter(cartao -> cartao.getBandeira().equals(relatorio.getCartao().getBandeira()))
						.collect(Collectors.toSet()));
				return p;
			}).collect(Collectors.toList());
		}
		
		
		if(relatorio.getStatus() != null) {
			pedidos = pedidos
					.stream()
					.filter(p -> p.getStatusPedido().equals(relatorio.getStatus())).collect(Collectors.toList());
		}
		
		return noCast(pedidos);
		
	}

	@Override
	public void deletar(IEntidade entidade) {
		
	}

}
