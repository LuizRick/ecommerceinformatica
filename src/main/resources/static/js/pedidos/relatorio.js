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
				labels: ['Janeiro' , 'Fevereiro' , 'Marco' , 'Abril' , 'Maio' ,
					'Junho' , 'Julho' , 'Agosto' , 'Setembro' , 'Outrobro', 'Novembro', 'Dezembro'],
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
		console.log(pedidos);
		var data = new Array(12);
		data.fill(0);
		
		for(var i in data){
			var value = data[i];
			pedidos.forEach(pedido => {
				if(parseInt(i) + 1 == pedido.created[1]){
					pedido.itens.forEach( v => value += v.quantidade);
				}
			});
			data[i]  = value;
		}
		
		return data;
	}

	function getDataBandeiraFromPedidos(pedidos){
		var data = new Array(12);
		data.fill(0);
		
		for(var i in data){
			var value = data[i];
			pedidos.forEach(pedido => {
				if(parseInt(i) + 1 == pedido.created[1]){
					value += pedido.cartao.length;
				}
			});
			data[i]  = value;
		}
		
		return data;
	}

	function getDataStatusFromPedido(pedidos){
		var data = new Array(12);
		data.fill(0);
		
		for(var i in data){
			var value = data[i];
			pedidos.forEach(pedido => {
				if(parseInt(i) + 1 == pedido.created[1]){
					value++;
				}
			});
			data[i]  = value;
		}
		
		return data;
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
});