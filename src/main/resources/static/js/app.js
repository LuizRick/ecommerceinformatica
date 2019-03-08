require(['jquery'], function($){
	$("#grupoPrecificacao,#margeLucro,#valorCusto").on('change', () => {
		var grupo = parseFloat($("#grupoPrecificacao option:selected").attr("model-bind"));
		var valorCusto = AppUtils.zerarFloatInvalido($("#valorCusto").val());
		var novaMargem = (grupo * valorCusto) / 100;
		$("#margeLucro").val(novaMargem.toFixed(2));
		var margemLucro = AppUtils.zerarFloatInvalido($("#margeLucro").val());
		$("#valorVenda").val((valorCusto + margemLucro).toFixed(2));
	});
	
	$("#recalcularValorVendaBtn").on('click', () => $("#grupoPrecificacao").trigger("change"));
	
	
	$("#frmProduto").on('submit', () => {
		$("#margeLucro,#valorCusto,#valorVenda").each((i, elt) => {
			if(Number.isNaN($(elt).val())){
				$(elt).addClass('invalid');
			}else{
				$(elt).removeClass('invalid');
			}
		});
		
		
		if($("#frmProduto *").hasClass('invalid')){
			console.log('invalid');
		}
		return false;
	});
});

var AppUtils = {
	zerarFloatInvalido: function(valor){
		//zerar o valor se for invalido
		var num = parseFloat(valor);
		if(Number.isNaN(num))
			return 0;
		else
			return num;
	}	
};