package com.les.ecommerce.application.venda;

import java.util.List;

import com.les.ecommerce.model.EntidadeAplicacao;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.cliente.IFormaPagamento;

public class Pedido extends EntidadeAplicacao {
	
	private Cliente cliente;
	private Endereco endereco;
	private List<IFormaPagamento> formasPagamento;
	private Carrinho carrinho;
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<IFormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}
	public void setFormasPagamento(List<IFormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
}
