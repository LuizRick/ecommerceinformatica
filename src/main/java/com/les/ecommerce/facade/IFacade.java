package com.les.ecommerce.facade;

import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;

@Component
public interface IFacade {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado visualizar(EntidadeDominio entidade);
}
