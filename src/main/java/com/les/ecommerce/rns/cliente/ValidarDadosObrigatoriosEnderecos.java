package com.les.ecommerce.rns.cliente;

import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDadosObrigatoriosEnderecos implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		StringBuilder sb = new StringBuilder();
		
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getEnderecos() != null) {
			cliente.getEnderecos().stream().forEach( endereco -> {
				int index = cliente.getEnderecos().indexOf(endereco) + 1;
				if(StringHelper.isNullOrEmpty(endereco.getDescricao())) {
					sb.append("A descrição do endereço " + index + " não pode ser vazio <br/>");
				}
					
				if(StringHelper.isNullOrEmpty(endereco.getTipoResidencia())) {
					sb.append("O tipo da residencia do endereço " + index + " não pode ser vazio<br/>");
				}
				
				if(StringHelper.isNullOrEmpty(endereco.getTipoLogradouro())) {
					sb.append("O tipo de logradouro do endereço" + index + " não pode ser vazio<br/>");
				}
				
				if(StringHelper.isNullOrEmpty(endereco.getNumero())) {
					sb.append("O numero do endereço" + index + " e de preencimento obrigatorio<br/>");
				}
				
				if(StringHelper.isNullOrEmpty(endereco.getBairro())) {
					sb.append("O bairro do endereço" + index + " e de preenchimento obrigatorio<br/>");
				}
				
				if(StringHelper.isNullOrEmpty(endereco.getCep())) {
					sb.append("O cep do endereço " + index + " e de preenchimento obrigatorio<br/>");
				}
				
				if(StringHelper.isNullOrEmpty(endereco.getPais())){
					sb.append("O pais do endereço " + index + " e de preenchimento obrigatorio<br/>");
				}
				
				
				if(StringHelper.isNullOrEmpty(endereco.getEstado())) {
					sb.append("O estado do endereço " + index + " e de preenchimento obrigatorio<br/>");
				}
				
				
				if(StringHelper.isNullOrEmpty(endereco.getCidade())) {
					sb.append("O cidade do endereço " + index + " e de preenchimento obrigatorio<br/>");
				}
			});
		}
		
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}

}
