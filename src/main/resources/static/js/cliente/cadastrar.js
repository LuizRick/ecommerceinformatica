requirejs(["vue", "jquery"], function(Vue, $){
	new Vue({
		el:'#app',
		data:{
			selectedDiv:'A',
			action:'SALVAR'
		},
		methods:{
			setTab:function(tab){
				this.selectedDiv = tab;
			},
			isActive:function(tab){
				return this.selectedDiv == tab;
			},
			addEndereco:function(){
				$("#action").val("addEndereco");
				$("#frmCliente").submit();
			},
			salvarCliente:function(){
				$("#action").val("SALVAR");
				$("#frmCliente").submit();
			},
			editarCliente:function(){
				$("#action").val("ALTERAR");
				$("#frmCliente").submit();
			},
			removerEndereco:function(index){
				$("#action").val("removerEndereco:" + index);
				$("#frmCliente").submit();
			},
			addCartao:function(index){
				$("#action").val("addCartao");
				$("#frmCliente").submit();
			},
			removerCartao:function(index){
				$("#action").val("removerCartao:" + index);
				$("#frmCliente").submit();
			}
		}
	});
});


