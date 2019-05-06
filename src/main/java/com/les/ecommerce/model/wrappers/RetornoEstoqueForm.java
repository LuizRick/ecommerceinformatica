package com.les.ecommerce.model.wrappers;

import java.util.ArrayList;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.INotPersistente;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;

public class RetornoEstoqueForm implements IEntidade,INotPersistente {

	private ArrayList<ItemPedido> itensRetorno;
	
	private Pedido pedido;

	public ArrayList<ItemPedido> getItensRetorno() {
		return itensRetorno;
	}

	public void setItensRetorno(ArrayList<ItemPedido> itensRetorno) {
		this.itensRetorno = itensRetorno;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
