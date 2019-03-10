package com.les.ecommerce.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			return "/errors/error-" + statusCode;
		}
		
		
		return "/errors/error";
	}
	
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
