package com.les.ecommerce.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.RequireJS;

import com.les.ecommerce.application.pagination.AbstractPagination;
import com.les.ecommerce.application.pagination.Pagination;
import com.les.ecommerce.application.spark.SparkContextLocal;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.helpers.ClienteHelper;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.model.venda.StatusPedido;

import reactor.core.publisher.Flux;
@Controller
public class HomeController extends BaseController {

	@Autowired
	private SimpMessagingTemplate messageTemplate;

	@Autowired
	private ClienteHelper clienteHelper;
	

	@ResponseBody
	@RequestMapping(value = "/webjarsjs", produces = "application/javascript")
	public String webjarjs() {
		return RequireJS.getSetupJavaScript("/webjars/");
	}

	@RequestMapping("/")
	public String home(Model model,SparkContextLocal spark) {
		Produto produto = new Produto();
		produto.setStatus(true);
		AbstractPagination pagination = new Pagination();
		pagination.setPageRequest(PageRequest.of(0, Integer.MAX_VALUE));
		Resultado resultado = this.commands.get(CONSULTAR).execute(produto);
		model.addAttribute("produtos", resultado.getEntidades());
		model.addAttribute("item", new ItemCarrinho());
		return "views/home";
	}

	@GetMapping(value = "/trocas-chanel/stream")
	public Flux<ServerSentEvent<Pedido>> streamPedido(Authentication auth) {
		return Flux.interval(Duration.ofSeconds(3)).map(sequence -> {
			Pedido pedido = new Pedido();
			if (auth != null && this.hasRole("USER", auth.getAuthorities())) {
				Cliente cliente = clienteHelper.getClienteAuth(auth);
				pedido.setCliente(cliente);
				pedido.setStatusPedido(StatusPedido.TROCA_AUTORIZADA);
				Resultado resultado = this.commands.get(CONSULTAR).execute(pedido);
				if (resultado.getEntidades() != null && resultado.getEntidades().size() > 0) {
					Pedido findPedido = (Pedido) resultado.getEntidades().get(0);
					pedido.setId(findPedido.getId());
					pedido.setCliente(findPedido.getCliente());
				}
			}
			return ServerSentEvent.<Pedido>builder().id(String.valueOf(sequence)).event("message")
					.data(pedido).build();
		});
	}

	@MessageMapping("/session-chanel")
	public void sessionMessages(SimpMessageHeaderAccessor header) throws Exception {
		this.messageTemplate.convertAndSend("");
	}

	@RequestMapping("/sair")
	public String sair() {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/access-denied")
	public String acessoNegado() {
		return "views/conta/acesso-negado";
	}
}
