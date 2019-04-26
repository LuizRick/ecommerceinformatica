$("#frmPedido").on('submit', () => {
	
});


$("[name='enderecoEntrega.id']").on('change',function(){
	$.ajax({
		url:"/pedido/frete/calcula",
		data:{
			index:$(this).val()
		},
		success:function(data){
			$("#txtValorFrete").text(data.toFixed(2));
			$("#valorFrete").val(data);
		}
	});
});