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
			sb.append("A descrição é um campo obrigatorio <br/>");
		}
		
		if(StringHelper.isNullOrEmpty(produto.getMarca())) {
			sb.append("A marca é um campo obrigatorio <br/>");
		}
		
		if(StringHelper.isNullOrEmpty(produto.getModelo())) {
			sb.append("O modelo é um campo obrigatorio <br/>");
		}
		
		if(produto.getPeso() == null) {
			sb.append("O peso é um campo obrigatorio <br/>");
		}
		
		if(produto.getAltura() == null) {
			sb.append("A altura é um campo obrigatorio <br/>");
		}
		
		
		if(produto.getLargura() == null) {
			sb.append("A largura é um campo obrigatorio <br/>");
		}
		
		if(StringHelper.isNullOrEmpty(produto.getCodigoBarras())) {
			sb.append("O codigo de barras é um campo obrigatorio <br/>");
		}
		
		if(produto.getValorCusto() == null) {
			sb.append("O valor de custo e um campos obrigatorio <br/>");
		}
		
		if(produto.getValorVenda() == null) {
			sb.append("O valor de vanda é um campo obrigatorio <br/>");
		}
		
		if(produto.getMargeLucro() == null) {
			sb.append("A margem de lucro é um campo obrigatorio <br/>");
		}
		
		
		if(produto.getDepartamento() == null || produto.getDepartamento().getId() <= 0) {
			sb.append("E necessario selecionar um departamento valido <br/>");
		}
		
		
		if(produto.getGrupoPrecificacao() == null || produto.getGrupoPrecificacao().getId() <= 0) {
			sb.append("É necessário selecionar um grupo de precificação valido <br/>");
		}
		
		if(produto.getEstoque() != null && produto.getEstoque() < 0) {
			sb.append("A entrada no estoque não pode ser negativa");
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}
}
