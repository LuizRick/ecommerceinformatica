package com.les.ecommerce.controller.venda;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.les.ecommerce.application.venda.Relatorio;
import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.produto.Departamento;
import com.les.ecommerce.model.produto.GrupoPrecificacao;

@RequestMapping("/relatorio")
@Controller
public class RelatoriosController extends BaseController {

	
	
	@RequestMapping("/vendas/home")
	public String home(Model model) {
		this.setarGrupoPrecificacao(model);
		return "views/relatorios/home";
	}
	
	@RequestMapping("/vendas/grafico")
	@ResponseBody
	public Resultado consultar(Relatorio relatorio){
		return this.commands.get(CONSULTAR).execute(relatorio);
	}
	
	
	private void setarGrupoPrecificacao(Model model) {
		GrupoPrecificacao grupo = new GrupoPrecificacao();
		Departamento depart = new Departamento();
		model.addAttribute("grupos",commands.get(CONSULTAR).execute(grupo).getEntidades());
		model.addAttribute("departs",commands.get(CONSULTAR).execute(depart).getEntidades());
	}
}
