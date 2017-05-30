		
	$(function() {		
		$('.textfield').on("keyup", function() {
			localStorage.setItem("val", $(this).val());
		});
	});


	
	