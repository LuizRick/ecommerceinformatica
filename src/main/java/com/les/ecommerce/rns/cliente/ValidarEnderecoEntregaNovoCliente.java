package com.les.ecommerce.rns.cliente;

import java.util.List;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.cliente.TipoEndereco;
import com.les.ecommerce.rns.IStrategy;

public class ValidarEnderecoEntregaNovoCliente implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getEnderecos() == null ||
				cliente.getEnderecos().size() <= 0 || 
				!hasEnderecoEntrega(cliente.getEnderecos()) ) {
			return "o cliente necessita de um endereço de entrega";
		}
		return null;
	}
	
	
	private boolean hasEnderecoEntrega(List<Endereco> enderecos) {
		
		for(Endereco endereco : enderecos) {
			if(endereco.getTipo() == TipoEndereco.ENTREGA)
				return true;
		}
		
		return false;
	}
}
