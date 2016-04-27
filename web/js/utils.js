//自动调整内嵌iframe子窗口的高度
function reinitIframe(id){
	var iframe = document.getElementById(id);	
	try{	
		var bHeight = iframe.contentWindow.document.body.scrollHeight;		
		//var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;		
		var height = bHeight;		
		iframe.height =  height;	
	}catch (ex){}	
}