package com.les.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.RequireJS;

@Controller
public class HomeController {
	
	@ResponseBody
	@RequestMapping(value = "/webjarsjs", produces = "application/javascript")
	public String webjarjs() {
	    return RequireJS.getSetupJavaScript("/webjars/");
	}
	
	
	@RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Hello World!");
        return "views/home";
    }
	
}
