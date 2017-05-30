
	$(function() {
		$("#naam").on("keyup", function() {		onkeyup(this);		});
		$('.modal').modal();
		loadCountries();
	});
	
	
	function onkeyup(e) {
		var value = $(e).val();
		$("tbody tr td.land").each(function(index) {
			if($(this).text().toLowerCase().indexOf(value.toLowerCase()) > -1) $(this).closest("tr").removeClass("hide");
			else $(this).closest("tr").addClass("hide");
		});		
	}


	function loadCountries() {			
		$.getJSON("http://localhost:4711/firstapp/restservices/countries", function(data) {					
			$.each(data, function(i, value) {
				$("tbody").append("<tr><td class='land'>" + data[i]["name"] + "</td><td>" + data[i]["capital"] + "</td><td>" + data[i]["region"] + "</td><td>" + data[i]["surfce"] + "</td><td>" + data[i]["population"] + "</td><td><i class='material-icons delete' code='" + data[i]["code"] + "'>delete</i><i class='material-icons update' code='" + data[i]["code"] + "'>mode_edit</i></tr>")			
			});			
			$(".material-icons.delete").on("click", function() {		deleteCountry($(this).attr("code"));		})
			$(".material-icons.update").on("click", function() {		updateCountry($(this).attr("code"));		})
		});	
	}


	function deleteCountry(code) {
		$.ajax({
		    url: 'http://localhost:4711/firstapp/restservices/countries/' + code,
		    type: 'DELETE',
		    contentType:'application/json',  
		    dataType: 'text'
		});	
	}


	function updateCountry(code) {
		$.getJSON("http://localhost:4711/firstapp/restservices/countries/" + code, function(data) {			
  			$.each(data, function(key, value) {
   				$('[name='+key+']', $("#formJson")).val(value);
  			});
		});		
		$('#modal').modal('open'); 
	}


	function updateRequest() {
		var data = $("#formJson").serialize(); 		
		$.ajax({
		    url: 'http://localhost:4711/firstapp/restservices/countries/',
		    type: 'PUT',
		    data: data, 
		    contentType:'application/x-www-form-urlencoded', 
		    dataType: 'text'
		});

		setTimeout(function() {
			$("tbody").html("");
			loadCountries();
		}, 1000)
	}


	function saveRequest() {
		var data = $("#formJson2").serialize(); 		
		$.ajax({
		    url: 'http://localhost:4711/firstapp/restservices/countries/',
		    type: 'POST',
		    data: data, 
		    contentType:'application/x-www-form-urlencoded', 
		    dataType: 'text'
		});

		

		setTimeout(function() {
			$("tbody").html("");
			loadCountries();
		}, 1000)
	}


	
