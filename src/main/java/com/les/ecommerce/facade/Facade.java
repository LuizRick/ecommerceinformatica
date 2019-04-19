package com.les.ecommerce.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.IDAO;
import com.les.ecommerce.dao.cliente.ClienteDAO;
import com.les.ecommerce.dao.produto.DepartamentoDAO;
import com.les.ecommerce.dao.produto.GrupoPrecificacaoDAO;
import com.les.ecommerce.dao.produto.ProdutoDAO;
import com.les.ecommerce.dao.usuario.UserDAO;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.INotPersistente;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.produto.Departamento;
import com.les.ecommerce.model.produto.GrupoPrecificacao;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;
import com.les.ecommerce.rns.cliente.ValidarCartaoObrigatorioNovoCliente;
import com.les.ecommerce.rns.cliente.ValidarConfirmacaoSenha;
import com.les.ecommerce.rns.cliente.ValidarDadosObrigatoriosCartoes;
import com.les.ecommerce.rns.cliente.ValidarDadosObrigatoriosCliente;
import com.les.ecommerce.rns.cliente.ValidarDadosObrigatoriosEnderecos;
import com.les.ecommerce.rns.cliente.ValidarEmailUnicoCliente;
import com.les.ecommerce.rns.cliente.ValidarEnderecoCobrancaNovoCliente;
import com.les.ecommerce.rns.cliente.ValidarEnderecoEntregaNovoCliente;
import com.les.ecommerce.rns.cliente.ValidarSenhaForteCliente;
import com.les.ecommerce.rns.produto.ValidarCategoriaTrocaStatus;
import com.les.ecommerce.rns.produto.ValidarDadosObrigatoriosProdutos;
import com.les.ecommerce.rns.produto.ValidarInativacaoProduto;
import com.les.ecommerce.rns.produto.ValidarProdutoAtivo;
import com.les.ecommerce.rns.produto.ValidarReentradaCadastroProduto;
import com.les.ecommerce.rns.produto.ValidarValorVendaProduto;
import com.les.ecommerce.rns.user.ValidarEmailUnico;
import com.les.ecommerce.rns.user.ValidarSenhaForteUser;
import com.les.ecommerce.rns.vendas.ValidarDadosAddCarrinho;
import com.les.ecommerce.rns.vendas.ValidarQuantidadeEstoqueAddCarrinho;

@Component
public class Facade  implements IFacade{
	private Resultado resultado;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Map<String, IDAO> repositories;
	
	private static final String SALVAR = "SALVAR";
	private static final String ALTERAR = "ALTERAR";
	@SuppressWarnings("unused")
	private static final String CONSULTAR = "CONSULTAR";
	@SuppressWarnings("unused")
	private static final String DELETAR = "DELETAR";
	
	@Autowired
	private GrupoPrecificacaoDAO grupoPrecificacaoDAO;
	
	@Autowired
	private DepartamentoDAO departamentoDAO;
	
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@PostConstruct
	public void init() {
		repositories = new HashMap<>();
		rns = new HashMap<>();
		
		//regras para produto
		List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarProduto = new ArrayList<IStrategy>();
		Map<String, List<IStrategy>> rnsProduto = new HashMap<>();
		
		
		
		//regras para cliente
		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
		
		//regras para carrinho
		List<IStrategy> rnsSalvarCarrinho = new ArrayList<IStrategy>();
		
		
		Map<String, List<IStrategy>> rnsCliente = new HashMap<>();
		Map<String, List<IStrategy>> rnsCartao = new HashMap<>();
		
		
		//regras para user
		List<IStrategy> rnsSalvarUser = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarCartao = new ArrayList<IStrategy>();
		
		rnsSalvarProduto.add(new ValidarDadosObrigatoriosProdutos());
		rnsSalvarProduto.add(new ValidarValorVendaProduto());
		rnsSalvarProduto.add(new ValidarProdutoAtivo());
		rnsSalvarProduto.add(new ValidarReentradaCadastroProduto());
		rnsAlterarProduto.add(new ValidarInativacaoProduto());
		rnsAlterarProduto.add(new ValidarCategoriaTrocaStatus());
		
		rnsSalvarCliente.add(new ValidarDadosObrigatoriosCliente());
		rnsSalvarCliente.add(new ValidarCartaoObrigatorioNovoCliente());
		rnsSalvarCliente.add(new ValidarConfirmacaoSenha());
		rnsSalvarCliente.add(new ValidarEnderecoEntregaNovoCliente());
		rnsSalvarCliente.add(new ValidarSenhaForteCliente());
		rnsSalvarCliente.add(new ValidarEnderecoCobrancaNovoCliente());
		rnsSalvarCliente.add(new ValidarDadosObrigatoriosEnderecos());
		rnsSalvarCliente.add(new ValidarDadosObrigatoriosCartoes());
		rnsSalvarCliente.add(new ValidarEmailUnicoCliente());
		
		
		rnsAlterarCliente.add(new ValidarDadosObrigatoriosCliente());
		rnsAlterarCliente.add(new ValidarCartaoObrigatorioNovoCliente());
		rnsAlterarCliente.add(new ValidarEnderecoEntregaNovoCliente());
		rnsAlterarCliente.add(new ValidarEnderecoCobrancaNovoCliente());
		rnsAlterarCliente.add(new ValidarDadosObrigatoriosEnderecos());
		rnsAlterarCliente.add(new ValidarDadosObrigatoriosCartoes());
		rnsAlterarCliente.add(new ValidarEmailUnicoCliente());
		
		Map<String, List<IStrategy>> rnsCarrinho = new HashMap<>();
		rnsSalvarCarrinho.add(new ValidarDadosAddCarrinho());
		rnsSalvarCarrinho.add(new ValidarQuantidadeEstoqueAddCarrinho());
		
		
		rnsSalvarUser.add(new com.les.ecommerce.rns.user.ValidarConfirmacaoSenha());
		rnsSalvarUser.add(new ValidarSenhaForteUser());
		rnsSalvarUser.add(new ValidarEmailUnico());
		Map<String, List<IStrategy>> rnsUser = new HashMap<>();
		
		
		
		
		
		rnsProduto.put(SALVAR, rnsSalvarProduto);
		rnsProduto.put(ALTERAR, rnsAlterarProduto);
		
		rnsCliente.put(SALVAR, rnsSalvarCliente);
		rnsCliente.put(ALTERAR, rnsAlterarCliente);
		
		
		rnsCarrinho.put(SALVAR, rnsSalvarCarrinho);
		
		rnsUser.put(SALVAR, rnsSalvarUser);
		
		repositories.put(GrupoPrecificacao.class.getName(), grupoPrecificacaoDAO);
		repositories.put(Departamento.class.getName(), departamentoDAO);
		repositories.put(Produto.class.getName(), produtoDAO);
		repositories.put(Cliente.class.getName(),clienteDAO);
		repositories.put(User.class.getName(), userDAO);
		
		rns.put(Produto.class.getName(),rnsProduto);
		rns.put(Cliente.class.getName(), rnsCliente);
		rns.put(Carrinho.class.getName(),rnsCarrinho);
		rns.put(User.class.getName(), rnsUser);
	}

	@Override
	public Resultado salvar(IEntidade entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "SALVAR");
		if (msg == null && entidade instanceof EntidadeDominio && !(entidade instanceof INotPersistente)) {
			try {
				repositories.get(nmClass).salvar(entidade);
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel salvar os dados");
			}
		} else {
			resultado.setMsg(msg);
			List<IEntidade> entidades = new ArrayList<>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
		}
		return resultado;
	}

	@Override
	public Resultado alterar(IEntidade entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "ALTERAR");
		if (msg == null && entidade instanceof EntidadeDominio && !(entidade instanceof INotPersistente)) {
			try {
				repositories.get(nmClass).alterar(entidade);
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel alterar os dados");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(IEntidade entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "EXCLUIR");
		if (msg == null && entidade instanceof EntidadeDominio && !(entidade instanceof INotPersistente)) {
			try {
				repositories.get(nmClass).deletar(entidade);
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel excluir os dados");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	@Override
	public Resultado consultar(IEntidade entidade) {
		resultado = new Resultado();
		String nmClass = entidade.getClass().getName();
		String msg = executaRegras(entidade, "CONSULTAR");
		if (msg == null && entidade instanceof EntidadeDominio && !(entidade instanceof INotPersistente)) {
			try {
				resultado.setEntidades(repositories.get(nmClass).consultar(entidade));
			}catch(Exception ex) {
				ex.printStackTrace();
				resultado.setMsg("Não foi possivel consultar os dados");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado visualizar(IEntidade entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<IEntidade>(1));
		resultado.getEntidades().add(entidade);
		return resultado;
	}

	private String executaRegras(IEntidade entidade, String operacao) {
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
