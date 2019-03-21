requirejs(["vue"], function(Vue){
	new Vue({
		el:'#app',
		data:{
			selectedDiv:'A'
		},
		methods:{
			setTab:function(tab){
				this.selectedDiv = tab;
			},
			isActive:function(tab){
				return this.selectedDiv == tab;
			}
		}
	})
});