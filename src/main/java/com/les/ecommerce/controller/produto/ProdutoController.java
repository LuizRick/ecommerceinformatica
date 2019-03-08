package com.les.ecommerce.controller.produto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
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
	public String visualizar(@PathVariable("id") Integer id) {
		return "views/produto/cadastrar";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String salvar(Produto produto,Model model) {
		produto.setStatus(true);
		produto.setCreated(LocalDateTime.now());
		Resultado resultado = commands.get(SALVAR).execute(produto);
		model.addAttribute("resultado", resultado);
		if(resultado.getMsg() == null || resultado.getMsg().length() <=0) {
			resultado.setMsg("Produto foi salvo com sucesso");
			return "redirect:views/produto/consultar";
		}
		setarGrupoPrecificacao(model);
		return "views/produto/cadastrar";
	}
	
	@RequestMapping(value="/consultar", method=RequestMethod.GET)
	public String consultar(Produto produto, Model model) {
		setarGrupoPrecificacao(model);
		return "views/produto/consultar";
	}
	
	
	@RequestMapping(value="/consutlar", method=RequestMethod.POST)
	public String resultadoConsulta(Produto produto,Model model) {
		Resultado resultado = commands.get(CONSULTAR).execute(produto);
		model.addAttribute("produtos", resultado.getEntidades());
		setarGrupoPrecificacao(model);
		return "views/produto/consultar";
	}
	
	
	private void setarGrupoPrecificacao(Model model) {
		GrupoPrecificacao grupo = new GrupoPrecificacao();
		Departamento depart = new Departamento();
		model.addAttribute("grupos",commands.get(CONSULTAR).execute(grupo).getEntidades());
		model.addAttribute("departs",commands.get(CONSULTAR).execute(depart).getEntidades());
	}
	
}
