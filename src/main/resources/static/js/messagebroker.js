(function(){
	var eventSource = new EventSource("/trocas-chanel/stream");
	console.log(eventSource);
	eventSource.onmessage = function(event){
		var pedido = JSON.parse(event.data);
		if(pedido.id){
			$(".msg-info").html(`Ola <b>${pedido.cliente.nome}</b> seu pedido de número ${pedido.id} esta com status ${pedido.statusPedido}`);
		}
	}
	
	eventSource.onerror = function(){
		console.log(arguments);
	}
}());