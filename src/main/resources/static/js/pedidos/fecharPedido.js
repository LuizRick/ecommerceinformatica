
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

$("#listaCupons").on('click',".cupom-item",function(){
	var index = $(this).attr("data-index");
	$("#listaCupons .cupom-item").eq(index).remove();
	
	
	$("#listaCupons .cupom-item").each( (i , element) => {
		$(element).find("input[type='hidden']").attr("name", `cupom[${i}].id`);
		$(element).attr("data-index", i);
	});
});

$("#btnVerificarCupom").on('click', function(){
	$.ajax({
		url:'/pedido/cupom/get',
		data:{
			cupom:$("#cupom").val()
		},
		success:function(data){
			if(data){
				var len = $(".cupom-item").length;
				$("#listaCupons").append(`
						<a href="javascript:void(0)" class="list-group-item list-group-item-action cupom-item" title="remover" data-index="${len}">
							<input type="hidden" name="cupom[${len}].id" value="${data.id}" />
							<div class="d-flex w-100 justify-content-between">
								<h5>${data.codigo}</h5>
								<h6>R$ ${data.valor}</h6>
							</div>
						</a>
				`);
			}else{
				alert("Nao foi encontrado esse cupom");
			}
		}
	})
});