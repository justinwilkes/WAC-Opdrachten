	
	

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

			initWeatherInfo(data["lat"], data["lon"], data["city"]);
			loadCountries();
		});
	}


	function initWeatherInfo(lat, lon, city) {
		$(".banner.weather").text("Het weer in " + city);

		if(sessionStorage.getItem(lat + "," + lon) == null) {
			console.log("Land is nog niet opgeslagen in session storage: \n" + "lat: " + lat + "\nlon: " + lon)
			$.getJSON("http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=5bf74c3ab8006106a7439496e26b360b", function(data) {			

				sessionStorage.setItem((lat + "," + lon), JSON.stringify(data) + ";;" + Date.now())

				$("#temperatuur").text(Math.round((data["main"]["temp"] - 273.15) * 10) / 10 + " C");
				$("#luchtvochtigheid").text(data["main"]["humidity"] + "%");
				$("#windsnelheid").text(Math.round(data["wind"]["speed"] / 0.62137 * 10) / 10 + " km/u");
				
				if(data["wind"]["deg"] == 0) $("#windrichting").text("Noorden");
				if(data["wind"]["deg"] == 90) $("#windrichting").text("Oosten");
				if(data["wind"]["deg"] == 180) $("#windrichting").text("Zuiden");
				if(data["wind"]["deg"] == 2700) $("#windrichting").text("Westen");

				if(data["wind"]["deg"] > 0 && data["wind"]["deg"] < 90) $("#windrichting").text("Noord-Oost");
				if(data["wind"]["deg"] > 90 && data["wind"]["deg"] < 180) $("#windrichting").text("Zuid-Oost");
				if(data["wind"]["deg"] > 180 && data["wind"]["deg"] < 270) $("#windrichting").text("Zuid-West");
				if(data["wind"]["deg"] > 270 && data["wind"]["deg"] < 360) $("#windrichting").text("Noord-West");
				
				var opkomst = new Date(data["sys"]["sunrise"]);			
				var ondergang = new Date(data["sys"]["sunset"]);	

				$("#zonsopgang").text(opkomst.getHours() + ":" + opkomst.getMinutes() + ":" + opkomst.getSeconds());
				$("#zonsondergang").text(ondergang.getHours() + ":" + ondergang.getMinutes() + ":" + ondergang.getSeconds());
			});

		} else {

			var dataSplitted = (sessionStorage.getItem(lat + "," + lon).split(";;"))
			
			var data = JSON.parse(dataSplitted[0])	
      		var time_now  = (new Date()).getTime();


  			if ((time_now - data[1]) > 1000 * 60 * 10) {
    			sessionStorage.removeItem(lat + "," + lon);
    			initWeatherInfo(lat, lon, city)
    			return;
  			}

			$("#temperatuur").text(Math.round((data["main"]["temp"] - 273.15) * 10) / 10 + " C");
			$("#luchtvochtigheid").text(data["main"]["humidity"] + "%");
			$("#windsnelheid").text(Math.round(data["wind"]["speed"] / 0.62137 * 10) / 10 + " km/u");
			
			if(data["wind"]["deg"] == 0) $("#windrichting").text("Noorden");
			if(data["wind"]["deg"] == 90) $("#windrichting").text("Oosten");
			if(data["wind"]["deg"] == 180) $("#windrichting").text("Zuiden");
			if(data["wind"]["deg"] == 2700) $("#windrichting").text("Westen");

			if(data["wind"]["deg"] > 0 && data["wind"]["deg"] < 90) $("#windrichting").text("Noord-Oost");
			if(data["wind"]["deg"] > 90 && data["wind"]["deg"] < 180) $("#windrichting").text("Zuid-Oost");
			if(data["wind"]["deg"] > 180 && data["wind"]["deg"] < 270) $("#windrichting").text("Zuid-West");
			if(data["wind"]["deg"] > 270 && data["wind"]["deg"] < 360) $("#windrichting").text("Noord-West");
			
			var opkomst = new Date(data["sys"]["sunrise"]);			
			var ondergang = new Date(data["sys"]["sunset"]);			


			$("#zonsopgang").text(opkomst.getHours() + ":" + opkomst.getMinutes() + ":" + opkomst.getSeconds());
			$("#zonsondergang").text(ondergang.getHours() + ":" + ondergang.getMinutes() + ":" + ondergang.getSeconds());



		}
	}


	function loadCountries() {		
		$("tbody").html("<tr><th>Land</th><th>Hoofdstad</th><th>Regio</th><th>Oppervlakte</th><th>Inwoners</th></tr>")	
		$.getJSON("/restservices/countries", function(data) {					
			$.each(data, function(i, value) {
				$("tbody").append("<tr onclick='showWeather(" + data[i]["lat"] + "," + data[i]["lat"] + ")'><td>" + data[i]["name"] + "</td><td>" + data[i]["capital"] + "</td><td>" + data[i]["region"] + "</td><td>" + data[i]["surfce"] + "</td><td>" + data[i]["population"] + "</td></tr>")			
			});			
		});	
	}

	function showWeather(lat, lon) {
		initWeatherInfo(lat, lon, "");
	}
