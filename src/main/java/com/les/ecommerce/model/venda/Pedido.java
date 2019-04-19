package com.les.ecommerce.model.venda;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.cliente.IFormaPagamento;

@Entity
public class Pedido extends EntidadeDominio implements IEntidade {

	@OneToOne
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;
	
	@OneToOne
	private EnderecoPedido enderecoEntrega;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<CartaoPedido> cartao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ItemPedido> itens;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Cupom> cupom;
	
	private Double valorFrete;
	
	@Transient 
	private Carrinho carrinho;
	
	@Transient
	private Set<IFormaPagamento> formasPagamento;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoPedido enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Set<CartaoPedido> getCartao() {
		return cartao;
	}

	public void setCartao(Set<CartaoPedido> cartao) {
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

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Set<IFormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(Set<IFormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	
}
