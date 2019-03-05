package com.les.ecommerce.rns.produto;

import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDadosObrigatoriosProdutos implements IStrategy {

	
	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		StringBuilder sb = new StringBuilder();
		
		if(StringHelper.isNullOrEmpty(produto.getDescricao())) {
			sb.append("A descrição é um campo obrigatorio \n");
		}
		
		if(StringHelper.isNullOrEmpty(produto.getMarca())) {
			sb.append("A marca é um campo obrigatorio \n");
		}
		
		if(StringHelper.isNullOrEmpty(produto.getModelo())) {
			sb.append("O modelo é um campo obrigatorio \n");
		}
		
		if(produto.getPeso() <= 0) {
			sb.append("O peso é um campo obrigatorio \n");
		}
		
		if(produto.getAltura() <=0) {
			sb.append("A altura é um campo obrigatorio \n");
		}
		
		
		if(produto.getLargura() <= 0) {
			sb.append("A largura é um campo obrigatorio \n");
		}
		
		if(StringHelper.isNullOrEmpty(produto.getCodigoBarras())) {
			sb.append("O codigo de barras é um campo obrigatorio \n");
		}
		
		if(produto.getValorCusto() <= 0) {
			sb.append("O valor de custo e um campos obrigatorio \n");
		}
		
		if(produto.getValorVenda() <= 0) {
			sb.append("O valor de vanda é um campo obrigatorio \n");
		}
		
		if(produto.getMargeLucro() <= 0) {
			sb.append("A margem de lucro é um campo obrigatorio \n");
		}
		
		
		if(produto.getDepart() == null || produto.getDepart().getId() <= 0) {
			sb.append("E necessario selecionar um departamento valido");
		}
		
		
		if(produto.getGrupo() == null || produto.getGrupo().getId() <= 0) {
			sb.append("O necessario selecionar um grupo de precificação valido");
		}	
		
		return null;
	}
}
