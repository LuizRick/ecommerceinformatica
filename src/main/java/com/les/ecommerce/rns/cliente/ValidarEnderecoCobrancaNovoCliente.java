package com.les.ecommerce.rns.cliente;

import java.util.List;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.cliente.EnderecoCliente;
import com.les.ecommerce.model.cliente.TipoEndereco;
import com.les.ecommerce.rns.IStrategy;

public class ValidarEnderecoCobrancaNovoCliente implements IStrategy {
	
	@Override
	public String processar(IEntidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getEnderecos() == null ||
				cliente.getEnderecos().size() <= 0 || 
				!hasEnderecoCobranca(cliente.getEnderecos()) ) {
			return "o cliente necessita de um endereço de cobrança";
		}
		return null;
	}
	
	
	private boolean hasEnderecoCobranca(List<EnderecoCliente> enderecos) {
		
		for(Endereco endereco : enderecos) {
			if(endereco.getTipo() == TipoEndereco.COBRANCA)
				return true;
		}
		
		return false;
	}

}
