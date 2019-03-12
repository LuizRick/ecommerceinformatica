$("#frmProduto").on('submit', () => {
	$("#margeLucro,#valorCusto,#valorVenda,#peso,#altura,#largura").each((i, elt) => {
		var valor = Number($(elt).val());
		if(Number.isNaN(valor)){
			$(elt).addClass('invalid');
		}else{
			$(elt).removeClass('invalid');
		}
	});
	
	
	if($("#frmProduto *").hasClass('invalid')){
		$("#modalGeral").modal('toggle').find('.modal-title').text("Formulário com campos invalidos");
		$("#modalGeral").find(".modal-body").html("Preencher corretamente os campos em vermelho");
		return false;
	}
});