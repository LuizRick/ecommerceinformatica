package com.les.ecommerce.model.venda;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.cliente.Endereco;

@Entity
public class Pedido extends EntidadeDominio implements IEntidade {

	@OneToOne
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, targetEntity=EnderecoPedido.class)
	private Endereco enderecoEntrega;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, targetEntity=CartaoPedido.class)
	private Set<Cartao> cartao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ItemPedido> itens;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Cupom> cupom;
	
	private Double valorFrete;
	
	private String msgPedido;

	public String getMsgPedido() {
		return msgPedido;
	}

	public void setMsgPedido(String msgPedido) {
		this.msgPedido = msgPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Set<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(Set<Cartao> cartao) {
		this.cartao = cartao;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Set<Cupom> getCupom() {
		return cupom;
	}

	public void setCupom(Set<Cupom> cupom) {
		this.cupom = cupom;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	
}
