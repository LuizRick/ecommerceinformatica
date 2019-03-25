package com.les.ecommerce.rns.cliente;

import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDadosObrigatoriosCartoes implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		StringBuilder sb = new  StringBuilder();
		
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getCartoes() != null) {
			
			cliente.getCartoes().stream().forEach(cartao -> {
				int i = cliente.getCartoes().indexOf(cartao);
				
				
				if(StringHelper.isNullOrEmpty(cartao.getTitular())) {
					sb.append("O titular do cartão " + i + " e de preenchimento obrigatorio<br/>");
				}
				if(StringHelper.isNullOrEmpty(cartao.getNumero())) {
					sb.append("O numero do cartão " + i + " e de preenchimento obrigatorio<br/>");
				}
				if(StringHelper.isNullOrEmpty(cartao.getCodigoSeguranca())) {
					sb.append("O codigo de segurança do cartão " + i + " e de preenchimento obrigatorio<br/>");
				}
				if(StringHelper.isNullOrEmpty(cartao.getBandeira())) {
					sb.append("A bandeira do cartão " + i + " e de preenchimento obrigatorio<br/>");
				}
				
			});
			
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}

}
