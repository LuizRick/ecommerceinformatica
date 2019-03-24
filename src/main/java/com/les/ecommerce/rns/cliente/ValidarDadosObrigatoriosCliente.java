package com.les.ecommerce.rns.cliente;

import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDadosObrigatoriosCliente implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		
		Cliente cliente = (Cliente) entidade;
		
		if(StringHelper.isNullOrEmpty(cliente.getNome())) {
			sb.append("O nome e um campo obrigatorio<br/>");
		}
		
		if(StringHelper.isNullOrEmpty(cliente.getDataNascimento())) {
			sb.append("A data de nascimento e um campo obrigatorio<br/>");
		}
		
		if(StringHelper.isNullOrEmpty(cliente.getCpf())) {
			sb.append("O cpf e um campo obrigatorio<br/>");
		}
		
		if(!StringHelper.isNullOrEmpty(cliente.getUsuario()) 
				&& StringHelper.isNullOrEmpty(cliente.getUsuario().getEmail())) {
			sb.append("O email e um campo obrigatorio<br/>");
		}
		
		if(cliente.getUsuario() != null && StringHelper.isNullOrEmpty(cliente.getUsuario().getPassword())){
			sb.append("A senha e um campo obrigatorio<br/>");
		}
		
		if(cliente.getTelefone() != null && StringHelper.isNullOrEmpty(cliente.getTelefone().getDdd())) {
			sb.append("O campo DDD do telefone e obrigatorio<br/>");
		}
		
		if(cliente.getTelefone() != null && StringHelper.isNullOrEmpty(cliente.getTelefone().getNumero())) {
			sb.append("E necessario um numero de telefone<br/>");
		}
		
		if(cliente.getGenero() == null) {
			sb.append("E necessario escolher um genero valido<br/>");
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}

}
