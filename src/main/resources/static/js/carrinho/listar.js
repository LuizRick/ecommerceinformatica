$("#quantidade").on('change', function(event){
	$("#modalGeral").modal('toggle').find('.modal-title').text("Aguarde...");
	$("#modalGeral").modal('toggle').find('.modal-body').text("Alterando quantidade");
	$("#btnFecharPedido").hide();
	var data  = { id: $(this).attr("data-cod"), quantidade: $(this).val() };
	$.ajax({
		url:'/carrinho/alterar',
		data:data,
		success: function(data){
			if(data.msg == null){
				$("#modalGeral").find('.modal-body').text("Quantidade alterada com sucesso");
			}else{
				$("#modalGeral").find('.modal-body').html(`<div class='alert alert-warning'>${data.msg}</div>`);
			}
			
			$("[data-dismiss]").on('click', function(){
				location.reload();
			});
		}
	});
});