package com.les.ecommerce.facade;

import org.springframework.stereotype.Component;

import com.les.ecommerce.model.IEntidade;

@Component
public interface IFacade {
	public Resultado salvar(IEntidade entidade);
	public Resultado alterar(IEntidade entidade);
	public Resultado excluir(IEntidade entidade);
	public Resultado consultar(IEntidade entidade);
	public Resultado visualizar(IEntidade entidade);
}
