package com.les.ecommerce.controller.cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Endereco;

@Controller
public class ClienteController extends BaseController {

	
	@RequestMapping(path="/admin/cliente/cadastro")
	public String cadastrar(Cliente cliente) {
		cliente.setEnderecos(new ArrayList<Endereco>());
		cliente.getEnderecos().add(new Endereco());
		cliente.setCartoes(new ArrayList<Cartao>());
		cliente.getCartoes().add(new Cartao());
		return "views/cliente/cadastrar";
	}
	
	@RequestMapping(path="/admin/clientes/consultar")
	public String pesquisar(Cliente cliente) {
		return "views/cliente/consultar";
	}
	
	@RequestMapping(path="/admin/clientes/consultar", method=RequestMethod.POST)
	public String consultar(Cliente cliente,Model model) {
		Resultado resultado = this.commands.get(CONSULTAR).execute(cliente);
		if(resultado.getEntidades() != null && resultado.getEntidades().size() > 0) {
			model.addAttribute("clientes", resultado.getEntidades());
		}
		return "views/cliente/consultar";
	}
	
	@RequestMapping(value="/admin/cliente/salvar",method=RequestMethod.POST)
	public String salvar(Cliente cliente,Model model,RedirectAttributes redirectAttributes) {
		if(cliente.getAction().equalsIgnoreCase("addEndereco")) {
			cliente.getEnderecos().add(new Endereco());
		}
		
		if(cliente.getAction().equalsIgnoreCase("addCartao")) {
			cliente.getCartoes().add(new Cartao());
		}
		
		if(cliente.getAction().contains("removerEndereco")) {
			int index = Integer.parseInt(cliente.getAction().split(":")[1]);
			cliente.getEnderecos().remove(index);
		}
		
		if(cliente.getAction().contains("removerCartao")) {
			int index = Integer.parseInt(cliente.getAction().split(":")[1]);
			cliente.getCartoes().remove(index);
		}
		
		if(cliente.getAction().equalsIgnoreCase(this.SALVAR)) {
			cliente.getUsuario().setActive(1);
			cliente.setCreated(LocalDateTime.now());
			Resultado resultado = this.commands.get(cliente.getAction()).execute(cliente);
			if(StringHelper.isNullOrEmpty(resultado.getMsg())) {
				resultado.setMsg("Cliente foi salvo com sucesso");
				redirectAttributes.addFlashAttribute("resultado", resultado);
				return "redirect:/admin/clientes/consultar";
			}
			model.addAttribute("resultado", resultado);
		}
		
		return "views/cliente/cadastrar";
	}
}
