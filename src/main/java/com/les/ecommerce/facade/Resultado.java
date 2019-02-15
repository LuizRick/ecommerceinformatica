package com.les.ecommerce.facade;

import java.util.List;
import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;


@Component
public class Resultado {
	private String msg;
	private List<EntidadeDominio> entidades;
	
	public String getMsg(){
		return msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}

	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
