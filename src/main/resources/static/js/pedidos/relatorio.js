$(document).ready(function(){
	var ctx = document.querySelector('#grafico').getContext('2d');
	
	var grafico = new Chart(ctx, {
		type:'line',
		data:{
			labels: ['Janeiro' , 'Fevereiro' , 'Marco' , 'Abril' , 'Maio' ,
				'Junho' , 'Julho' , 'Agosto' , 'Setembro' , 'Outrobro', 'Novembro', 'Dezembro'],
			datasets:[
				{
					label:'Departamento',
					data:[10,20,30,40,60,50,90,100,120,17,20,66],
					fill:false,
					borderColor:'rgba(120,90,90,1)'
				},
				{
					label:'Bandeira cartão',
					data:[108,290,308,40,608,508,908,1080,1280,187,208,686],
					fill:false,
					borderColor:'rgba(120,90,200,1)'
				}
			]
		},
		options: {
			responsive:true,
			title:{
				display:true,
				text:`Gráfico de vendas mensal do ano (aqui vai o ano)`
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
	
	
	$("#btnGerarRelatorio").on('click', function(){
		$.ajax({
			url:'/relatorio/vendas/grafico',
			method:'POST',
			data: getDataInputFromForm(),
			success: function(data){
				if(data.msg != null){
					$("#modalGeral").modal('toggle').find('.modal-title').text("Erro");
					$("#modalGeral").modal('toggle').find('.modal-body').html(data.msg);
				}
				
				console.dir(data.entidades);
				
			},
			error:function(){
				alert("Não foi possivel gerar o grafico")
			}
		});
	});
	
	
	function getDataInputFromForm(){
		var dataInicial =  IfElse(!isInputEmpty("#dataInicial"), () => $("#dataInicial").val() + "T00:00" , () => null);
		var dataFinal = IfElse(!isInputEmpty("#dataFinal"), () => $("#dataFinal").val() + "T00:00", () => null);
		var nome = IfElse(!isInputEmpty("#departamento"), () => $("#departamento").val(), () => null);
		var bandeira = IfElse(!isInputEmpty("#bandeira"), () => $("#bandeira").val(), () => null);
		var status =  IfElse(!isInputEmpty("#status"), () => $("#status").val(), () => null);
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