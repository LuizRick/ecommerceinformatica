package com.les.ecommerce.model.venda;

import java.util.Set;
import java.util.List;

import com.les.ecommerce.model.INotPersistente;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.cliente.IFormaPagamento;

public class Compra implements INotPersistente{
	
	private Carrinho carrinho;

	private Set<IFormaPagamento> formasPagamento;
	
	private Cliente cliente;
	
	private EnderecoPedido enderecoEntrega;
	
	private List<Cartao> cartao;
	
	private List<Cupom> cupom;
	
	private Double valorFrete;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EnderecoPedido getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoPedido endereco) {
		this.enderecoEntrega = endereco;
	}

	public List<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
	}

	public List<Cupom> getCupom() {
		return cupom;
	}

	public void setCupom(List<Cupom> cupom) {
		this.cupom = cupom;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
}
