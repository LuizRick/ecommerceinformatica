$(document).ready(function(){
	
	var grafico;
	
	$("#btnGerarRelatorio").on('click', function(){
		$.ajax({
			url:'/relatorio/vendas/grafico',
			method:'POST',
			data: JSON.stringify(getDataInputFromForm()),
			dataType: "json",
            contentType: "application/json",
			success: function(data){
				if(data.msg != null){
					$("#modalGeral").modal('toggle').find('.modal-title').text("Erro");
					$("#modalGeral").modal('toggle').find('.modal-body').html(data.msg);
				}else{
					gerarGrafico(data.entidades, getDataInputFromForm());
				}
				
				
			},
			error:function(){
				alert("Não foi possivel gerar o grafico")
			}
		});
	});
	
	
	
	function gerarGrafico(data,params){
		var ctx = document.querySelector('#grafico').getContext('2d');
		if(grafico){
			grafico.destroy();
		}
		grafico = new Chart(ctx, {
			type:'line',
			data:{
				labels: getLabelsFromPedidos(data),
				datasets:[
					{
						label:'Departamento :' + ((params.departamento.id == null) ? "Todos" : $("#departamento option:selected").text()),
						data:getDataDepartamentoFromPedidos(data),
						fill:false,
						borderColor:'rgba(120,90,90,1)'
					},
					{
						label:'Bandeira: ' + ((params.cartao.bandeira == null) ? "Todos" : params.cartao.bandeira),
						data:getDataBandeiraFromPedidos(data),
						fill:false,
						borderColor:'rgba(90,120,200,1)'
					},
					{
						label:'Status : ' + ((params.status == null) ? "Todos" : params.status),
						data:getDataStatusFromPedido(data),
						fill:false,
						borderColor:'rgba(120,200,10,1)'
					}
				]
			},
			options: {
				responsive:true,
				title:{
					display:true,
					text:`Gráfico de vendas mensal de ${ (params.dataInicial == null) ? 'Inicio' : new Date(params.dataInicial).toLocaleDateString()} até ${(params.dataFinal == null) ? 'Atualmente' : new Date(params.dataFinal).toLocaleDateString()}`
				},
				tooltips:{
					mode: 'index',
					intersect:false
				},
				hover:{
					mode:'nearest',
					interserct:true
				},
				scales:{
					xAxes:[{
						display:true,
						scaleLabel: {
							display:true,
							labelString:'Mês'
						}
					}],
					yAxes: [{
						display:true,
						scaleLabel:{
							display:true,
							labelString:'Total'
						}
					}]
				}
			}
		});
	}
	
	function getDataDepartamentoFromPedidos(pedidos){
		const groupPedido = pedidos.reduce((acc,currentValue,index,array) => {
			if(array[index + 1] && array[index + 1].created[1] == currentValue.created[1]){
				const prevSoma = currentValue.itens.reduce((acc, currentValue) => acc + currentValue.quantidade, 0);
				const nextSoma = array[index + 1].itens.reduce((acc, currentValue) => acc + currentValue.quantidade, 0);
				acc[currentValue.created[0] + "/" + currentValue.created[1]] = {
					soma:prevSoma + nextSoma
				};
				return acc;
			}
			acc[currentValue.created[0] + "/" + currentValue.created[1]] = {
				soma:currentValue.itens.reduce((acc, currentValue) => acc + currentValue.quantidade, 0)
			}
			return acc;
		},{});
		
		var somas = [];
		for(var x in groupPedido){
			somas.push(groupPedido[x].soma);
		}
		return somas;
	}

	function getDataBandeiraFromPedidos(pedidos){
		const groupPedido = pedidos.reduce((acc,currentValue,index,array) => {
			if(array[index + 1] && array[index + 1].created[1] == currentValue.created[1]){
				const prevSoma = currentValue.cartao.reduce((acc, currentValue) => acc + currentValue.length, 0);
				const nextSoma = array[index + 1].cartao.reduce((acc, currentValue) => acc + currentValue.length, 0);
				acc[currentValue.created[0] + "/" + currentValue.created[1]] = {
					soma:currentValue.cartao.length + array[index + 1].cartao.length
				};
				return acc;
			}
			acc[currentValue.created[0] + "/" + currentValue.created[1]] = {
				soma: currentValue.cartao.length
			}
			return acc;
		},{});
		
		var somas = [];
		for(var x in groupPedido){
			somas.push(groupPedido[x].soma);
		}
		return somas;
	}

	function getDataStatusFromPedido(pedidos){
		const groupPedido = pedidos.reduce((acc,currentValue,index,array) => {
			if(array[index + 1] && array[index + 1].created[1] == currentValue.created[1]){
				const prevSoma = currentValue.itens.reduce((acc, currentValue) => acc + currentValue.length, 0);
				const nextSoma = array[index + 1].itens.reduce((acc, currentValue) => acc + currentValue.length, 0);
				acc[currentValue.created[0] + "/" + currentValue.created[1]] = {
					soma:currentValue.length + array[index + 1].length
				};
				return acc;
			}
			acc[currentValue.created[0] + "/" + currentValue.created[1]] = {
				soma: currentValue.length
			}
			return acc;
		},{});
	}
	
	function getLabelsFromPedidos(pedidos){
		var map = ['Janeiro' , 'Fevereiro' , 'Marco' , 'Abril' , 'Maio' ,
			'Junho' , 'Julho' , 'Agosto' , 'Setembro' , 'Outrobro', 'Novembro', 'Dezembro'];
		
		var labels = [];
		
		for(var i in pedidos){
			labels.push(pedidos[i].created[1] + "/" + pedidos[i].created[0]);
		}
		
		var finalLabel = [];
		labels.forEach(label => {
			if(!finalLabel.includes(label)){
				finalLabel.push(label);
			}
		});
		
		return finalLabel;
	}

	
	function getDataInputFromForm(){
		var dataInicial =  IfElse(!isInputEmpty("#dataInicial"), () => $("#dataInicial").val() + "T00:00" , () => null);
		var dataFinal = IfElse(!isInputEmpty("#dataFinal"), () => $("#dataFinal").val() + "T00:00", () => null);
		var id = IfElse(!isInputEmpty("#departamento"), () => $("#departamento").val(), () => null);
		var bandeira = IfElse(!isInputEmpty("#bandeira"), () => $("#bandeira").val(), () => null);
		var status =  IfElse(!isInputEmpty("#status"), () => $("#status").val(), () => null);
		return {
			dataInicial,
			dataFinal,
			departamento:{
				id
			},
			cartao:{
				bandeira
			},
			status
		}
	}
	
	function isInputEmpty(x){
		return !$(x).val();
	}
	
	function IfElse(exp,funTrue,funFalse,args){
		if(exp){
			return funTrue.apply(this,args);
		}else{
			return funFalse.apply(this,args);
		}
	}
	
	function remove(array, element) {
	  const index = array.indexOf(element);
	  array.splice(index, 1);
	}
});