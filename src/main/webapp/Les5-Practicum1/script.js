		
	$(function() {
		initPage();
	});
	
	function initPage() {
		$.getJSON("http://ip-api.com/json", function(data) {			
			$("#landcode").text(data["countryCode"]);
			$("#land").text(data["country"]);
			$("#regio").text(data["regionName"]);
			$("#stad").text(data["city"]);
			$("#postcode").text(data["zip"]);
			$("#latitude").text(data["lat"]);
			$("#longitude").text(data["lon"]);
			$("#ip").text(data["query"]);		
		});
	}