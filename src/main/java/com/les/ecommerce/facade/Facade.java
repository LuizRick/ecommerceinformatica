package com.les.ecommerce.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.IDAO;
import com.les.ecommerce.dao.produto.DepartamentoDAO;
import com.les.ecommerce.dao.produto.GrupoPrecificacaoDAO;
import com.les.ecommerce.dao.produto.ProdutoDAO;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.Departamento;
import com.les.ecommerce.model.produto.GrupoPrecificacao;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;
import com.les.ecommerce.rns.produto.ValidarCategoriaTrocaStatus;
import com.les.ecommerce.rns.produto.ValidarDadosObrigatoriosProdutos;
import com.les.ecommerce.rns.produto.ValidarInativacaoProduto;
import com.les.ecommerce.rns.produto.ValidarProdutoAtivo;
import com.les.ecommerce.rns.produto.ValidarReentradaCadastroProduto;
import com.les.ecommerce.rns.produto.ValidarValorVendaProduto;

@Component
public class Facade  implements IFacade{
	private Resultado resultado;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Map<String, IDAO> repositories;
	
	@Autowired
	private GrupoPrecificacaoDAO grupoPrecificacaoDAO;
	
	@Autowired
	private DepartamentoDAO departamentoDAO;
	
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	ValidarReentradaCadastroProduto validarReentradaCadastroProduto;
	
	
	@PostConstruct
	public void init() {
		repositories = new HashMap<>();
		rns = new HashMap<>();
		
		//regras para produto
		List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterar = new ArrayList<IStrategy>();
		Map<String, List<IStrategy>> rnsProduto = new HashMap<>();
		
		
		rnsSalvarProduto.add(new ValidarDadosObrigatoriosProdutos());
		rnsSalvarProduto.add(new ValidarValorVendaProduto());
		rnsSalvarProduto.add(new ValidarProdutoAtivo());
		rnsSalvarProduto.add(validarReentradaCadastroProduto);
		rnsAlterar.add(new ValidarInativacaoProduto());
		rnsAlterar.add(new ValidarCategoriaTrocaStatus());
		rnsAlterar.add(new ValidarProdutoAtivo());
		
		rnsProduto.put("SALVAR", rnsSalvarProduto);
		rnsProduto.put("ALTERAR", rnsAlterar);
		repositories.put(GrupoPrecificacao.class.getName(), grupoPrecificacaoDAO);
		repositories.put(Departamento.class.getName(), departamentoDAO);
		repositories.put(Produto.class.getName(), produtoDAO);
		
		rns.put(Produto.class.getName(),rnsProduto);
		
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "SALVAR");
		if (msg == null) {
			try {
				repositories.get(nmClass).salvar(entidade);
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel salvar os dados");
			}
		} else {
			resultado.setMsg(msg);
			List<EntidadeDominio> entidades = new ArrayList<>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
		}
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "ALTERAR");
		if (msg == null) {
			try {
				repositories.get(nmClass).alterar(entidade);
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel salvar os dados");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "EXCLUIR");
		if (msg == null) {
			try {
				repositories.get(nmClass).deletar(entidade);
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel salvar os dados");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "CONSULTAR");
		if (msg == null) {
			try {
				resultado.setEntidades(repositories.get(nmClass).consultar(entidade));
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel salvar os dados");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);
		return resultado;
	}

	private String executaRegras(EntidadeDominio entidade, String operacao) {
		String nmClass = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClass);
		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);
			if (regras != null) {
				for (IStrategy s : regras) {
					String m = s.processar(entidade);
					if (m != null) {
						msg.append(m);
						msg.append("<br/>");
					}
				}
			}
		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}
}
