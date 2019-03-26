package com.les.ecommerce.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;

@Component
public interface IDAO {
	public void salvar(IEntidade entidade);
	public void alterar(IEntidade entidade);
	public List<EntidadeDominio> consultar(IEntidade entidade);
	public void deletar(IEntidade entidade);
}
