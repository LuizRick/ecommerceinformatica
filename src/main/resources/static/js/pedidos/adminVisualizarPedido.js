$("#btnDespachar").on('click', function(){
	$("#statusPedido").val("TRANSITO");
	$("#frmPedido").submit();
});

$("#btnEntregar").on('click', function(){
	$("#statusPedido").val("ENTREGUE");
	$("#frmPedido").submit();
});

$("#btnAutorizarTroca").on('click', function(){
	$("#statusPedido").val("TROCA_AUTORIZADA");
	$("#frmPedido").submit();
});


$("#btnRecebimento").on('click', function(){
	$("#statusPedido").val("TROCADO");
	$("[data-sel-item]").each(function(i, elt){
		console.dir(i);
		console.dir(elt);
	});
});