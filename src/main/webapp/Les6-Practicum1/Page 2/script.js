		
	window.addEventListener("storage", function(event) {
		$(".storage").text(event.newValue);
	})