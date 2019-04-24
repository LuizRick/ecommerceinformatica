$(function(){
	(function(){
		var url = new URL(location.href);
		var callback = url.searchParams.get("callback");
		
		if(callback && window[callback] instanceof Function){
			window[callback].call(this,Array.from(url.searchParams.entries()));
		}
	}())
});