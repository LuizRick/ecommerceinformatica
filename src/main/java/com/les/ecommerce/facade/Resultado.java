package com.les.ecommerce.facade;

import java.util.List;
import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;


@Component
public class Resultado {
	private String msg;
	private List<IEntidade> entidades;
	
	public String getMsg(){
		return msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}

	public List<IEntidade> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<IEntidade> entidades) {
		this.entidades = entidades;
	}
}
