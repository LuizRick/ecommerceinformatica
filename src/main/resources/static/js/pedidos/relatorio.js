$(document).ready(function(){
	
	
	
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
					console.dir(data.entidades);
				}
				
				
			},
			error:function(){
				alert("Não foi possivel gerar o grafico")
			}
		});
	});
	
	
	
	function gerarGrafico(data,params){
		var ctx = document.querySelector('#grafico').getContext('2d');
		
		var grafico = new Chart(ctx, {
			type:'line',
			data:{
				labels: ['Janeiro' , 'Fevereiro' , 'Marco' , 'Abril' , 'Maio' ,
					'Junho' , 'Julho' , 'Agosto' , 'Setembro' , 'Outrobro', 'Novembro', 'Dezembro'],
				datasets:[
					{
						label:'Departamento :' + ((params.departamento.id == null) ? "Todos" : $("#departamento option:selected").text()),
						data:[10,20,30,40,60,50,90,100,120,17,20,66],
						fill:false,
						borderColor:'rgba(120,90,90,1)'
					},
					{
						label:'Bandeira: ' + ((params.cartao.bandeira == null) ? "Todos" : param.cartao.bandeira),
						data:[108,290,308,40,608,508,908,1080,1280,187,208,686],
						fill:false,
						borderColor:'rgba(120,90,200,1)'
					},
					{
						label:'Status : ' + ((params.status == null) ? "Todos" : params.status),
						data:[18,29,38,82,68,58,98,180,180,183,223,623],
						fill:false,
						borderColor:'rgba(120,10,200,1)'
					}
				]
			},
			options: {
				responsive:true,
				title:{
					display:true,
					text:`Gráfico de vendas mensal de ${ (params.dataInicial == null) ? 'Inicio' : params.dataInicial} até ${(params.dataFinal == null) ? 'Atualmente' : params.dataFinal}`
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