package com.les.ecommerce.controller.produto;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.produto.Departamento;
import com.les.ecommerce.model.produto.GrupoPrecificacao;
import com.les.ecommerce.model.produto.Produto;

@Controller
@RequestMapping("/admin/produtos/")
public class ProdutoController extends BaseController {

	@RequestMapping(value="/cadastro", method=RequestMethod.GET)
	public String cadastro(final Produto produto,Model model) {
		setarGrupoPrecificacao(model);
		return "/views/produto/cadastrar";
	}
	
	
	@RequestMapping(value="/cadastro/{id}", method=RequestMethod.GET)
	public String visualizar(Produto produto,Model model,HttpSession sesssion) {
		setarGrupoPrecificacao(model);
		setProdutoModel(model, produto,sesssion);
		return "views/produto/cadastrar";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String salvar(Produto produto,Model model,RedirectAttributes redirectAttributes) {
		produto.setStatus(true);
		produto.setCreated(LocalDateTime.now());
		Resultado resultado = commands.get(SALVAR).execute(produto);
		model.addAttribute("resultado", resultado);
		if(StringHelper.isNullOrEmpty(resultado.getMsg())) {
			resultado.setMsg("Produto foi salvo com sucesso");
			redirectAttributes.addFlashAttribute("resultado", resultado);
			return "redirect:/admin/produtos/consultar";
		}
		setarGrupoPrecificacao(model);
		return "views/produto/cadastrar";
	}
	
	@RequestMapping(value="/consultar", method=RequestMethod.GET)
	public String consultar(Produto produto, Model model) {
		setarGrupoPrecificacao(model);
		return "views/produto/consultar";
	}
	
	
	@RequestMapping(value="/consultar", method=RequestMethod.POST)
	public String resultadoConsulta(Produto produto,Model model) {
		Resultado resultado = commands.get(CONSULTAR).execute(produto);
		model.addAttribute("produtos", resultado.getEntidades());
		setarGrupoPrecificacao(model);
		return "views/produto/consultar";
	}
	
	@RequestMapping(value="/inativar/{id}",method=RequestMethod.GET)
	public String inativar(Produto produto,Model model,HttpSession session) {
		setarGrupoPrecificacao(model);
		setProdutoModel(model, produto,session);
		return "views/produto/inativar";
	}
	
	
	@RequestMapping(value="/inativar", method=RequestMethod.POST)
	public String inativar(Produto produto,Model model,RedirectAttributes redirectAttributes, HttpSession session) {
		Produto entidade = (Produto) session.getAttribute("produtoSelecionado");
		entidade.setStatus(false);
		entidade.setCategoriaInativacao(produto.getCategoriaInativacao());
		entidade.setJustificativaInativacao(produto.getJustificativaInativacao());
		Resultado resultado = commands.get(ALTERAR).execute(entidade);
		model.addAttribute("resultado", resultado);
		if(StringHelper.isNullOrEmpty(resultado.getMsg())) {
			resultado.setMsg("Produto foi inativado com sucesso");
			redirectAttributes.addFlashAttribute("resultado", resultado);
			return "redirect:/admin/produtos/consultar";
		}
		return "views/produto/inativar";
	}
	
	
	@RequestMapping(value="/ativar/{id}",method=RequestMethod.GET)
	public String ativar(Produto produto,Model model, HttpSession session) {
		setarGrupoPrecificacao(model);
		setProdutoModel(model, produto,session);
		return "views/produto/ativar";
	}
	
	
	@RequestMapping(value="/ativar", method=RequestMethod.POST)
	public String ativar(Produto produto,Model model,RedirectAttributes redirectAttributes,HttpSession session) {
		Produto entidade = (Produto) session.getAttribute("produtoSelecionado");
		entidade.setStatus(true);
		entidade.setCategoriaInativacao(produto.getCategoriaInativacao());
		entidade.setJustificativaInativacao(produto.getJustificativaInativacao());
		Resultado resultado = commands.get(ALTERAR).execute(entidade);
		model.addAttribute("resultado", resultado);
		if(StringHelper.isNullOrEmpty(resultado.getMsg())) {
			resultado.setMsg("Produto foi ativado com sucesso");
			redirectAttributes.addFlashAttribute("resultado", resultado);
			return "redirect:/admin/produtos/consultar";
		}
		return "views/produto/ativar";
	}
	
	
	private void setarGrupoPrecificacao(Model model) {
		GrupoPrecificacao grupo = new GrupoPrecificacao();
		Departamento depart = new Departamento();
		model.addAttribute("grupos",commands.get(CONSULTAR).execute(grupo).getEntidades());
		model.addAttribute("departs",commands.get(CONSULTAR).execute(depart).getEntidades());
	}
	
	private void setProdutoModel(Model model,Produto produto,HttpSession session) {
		Resultado resultado = commands.get(CONSULTAR).execute(produto);
		if(StringHelper.isNullOrEmpty(resultado.getMsg()) && resultado.getEntidades().size() > 0) {
			produto = (Produto) resultado.getEntidades().get(0);
			model.addAttribute("produto", produto);
			session.setAttribute("produtoSelecionado", produto);
		}
	}
	
}
