
var oldValue = "";

setInterval(function(){ 
	var textarea = document.getElementById("textarea").value;
	if(textarea != oldValue) console.log(textarea);
	oldValue = textarea;
}, 5000);