package com.les.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.RequireJS;

import com.les.ecommerce.application.pagination.AbstractPagination;
import com.les.ecommerce.application.pagination.Pagination;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.produto.Produto;

@Controller
public class HomeController extends BaseController{
	
	@ResponseBody
	@RequestMapping(value = "/webjarsjs", produces = "application/javascript")
	public String webjarjs() {
	    return RequireJS.getSetupJavaScript("/webjars/");
	}
	
	
	@RequestMapping("/")
    public String home(Model model){
        Produto produto = new Produto();
        AbstractPagination pagination = new Pagination();
        produto.setAppData(pagination);
        Resultado resultado = this.commands.get(CONSULTAR).execute(produto);
        model.addAttribute("produtos", resultado.getEntidades());
        return "views/home";
    }
	
}
