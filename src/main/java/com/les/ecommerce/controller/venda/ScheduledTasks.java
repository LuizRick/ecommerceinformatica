package com.les.ecommerce.controller.venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.venda.FormasPagamento;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.model.venda.StatusPedido;

@Component
public class ScheduledTasks extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	/**
	 * Essa schedule tem como objetivo verificar os pedidos com status em
	 * processamento e validar a forma de pagamento e veracidade de informações da
	 * venda
	 */
	@Scheduled(fixedRate = 10000, initialDelay = 5000)
	public void verificarPedidosProcessamento() {
		Pedido pedido = new Pedido();
		pedido.setStatusPedido(StatusPedido.PROCESSAMENTO);
		Resultado resultado = this.commands.get(CONSULTAR).execute(pedido);
		if (resultado.getMsg() != null) {
			log.error(resultado.getMsg());
			return;
		}

		for (IEntidade entidade : resultado.getEntidades()) {
			Pedido p = (Pedido) entidade;
			FormasPagamento formaPagamento = new FormasPagamento();
			formaPagamento.setCartao(p.getCartao());
			formaPagamento.setCupons(p.getCupom());
			resultado = this.commands.get(CONSULTAR).execute(formaPagamento);
			if (resultado.getMsg() == null) {
				p.setStatusPedido(StatusPedido.APROVADO);
				for (ItemPedido i : p.getItens()) {
					i.getProduto().setAction("SALVARTASK");
					i.getProduto().setEstoque(i.getProduto().getEstoque() - i.getQuantidade());
					this.commands.get(ALTERAR).execute(i.getProduto());
				}
				
			} else {
				p.setStatusPedido(StatusPedido.REPROVADO);
				p.setMsgPedido(resultado.getMsg());
			}
			
			List<Cupom> cupons = new ArrayList<>();
			p.getCliente().getCupons().stream().filter(cup -> Objects.nonNull(cup)).forEach(c -> {
				p.getCupom().stream().filter(cup -> Objects.nonNull(cup)).forEach(cupomPedido -> {
					if(cupomPedido.getCodigo().equalsIgnoreCase(c.getCodigo())) {
						c.setAtivo(false);
					}
					cupons.add(c);
				});
			});
			
			p.getCliente().setCupons(cupons);
			p.getCliente().setAction("SALVARTASK");
			resultado = this.commands.get(ALTERAR).execute(p.getCliente());
			if (resultado.getMsg() != null) {
				p.setStatusPedido(StatusPedido.REPROVADO);
				p.setMsgPedido(resultado.getMsg().substring(0, 255));
			}
			
			resultado = this.commands.get(ALTERAR).execute(p);
			if (resultado.getMsg() != null) {
				log.error(resultado.getMsg());
			}
		}
	}
}
