
$("#grupoPrecificacao,#margeLucro,#valorCusto").on('change', () => {
	var grupo = parseFloat($("#grupoPrecificacao option:selected").attr("model-bind"));
	var valorCusto = AppUtils.zerarFloatInvalido($("#valorCusto").val());
	var novaMargem = (grupo * valorCusto) / 100;
	$("#margeLucro").val(novaMargem.toFixed(2));
	var margemLucro = AppUtils.zerarFloatInvalido($("#margeLucro").val());
	$("#valorVenda").val((valorCusto + margemLucro + 0.01).toFixed(2));
});

$("#recalcularValorVendaBtn").on('click', () => $("#grupoPrecificacao").trigger("change"));


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


var AppUtils = {
	zerarFloatInvalido: function(valor){
		//zerar o valor se for invalido
		var num = Number(valor);
		if(Number.isNaN(num))
			return 0;
		else
			return num;
	}	
};