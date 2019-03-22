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
			removerEndereco:function(index){
				$("#action").val("removeEndereco:" + index);
				$("#frmCliente").submit();
			}
		}
	});
});


