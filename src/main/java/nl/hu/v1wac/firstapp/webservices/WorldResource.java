package nl.hu.v1wac.firstapp.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import nl.hu.v1wac.firstapp.model.*;
	
	
@Path("countries/")
public class WorldResource {
	
	private JsonArrayBuilder jab = null;
	
	@GET
	@Produces("application/json")
	public String getAllCountries() {
		WorldService service = ServiceProvider.getWorldService();	    
		
		if(jab == null) {		
			jab = Json.createArrayBuilder();
		    for (Country c : service.getAllCountries()) {
		      JsonObjectBuilder job = Json.createObjectBuilder();
		      job.add("code", c.getCode());
		      job.add("iso3", c.getIso3Code());
		      job.add("name", c.getName());
		      job.add("continent", c.getContinent());
		      job.add("capital", c.getCapital());
		      job.add("region", c.getRegion());
		      job.add("surfce", c.getSurface());
		      job.add("population", c.getPopulation());
		      job.add("government", c.getGovernment());
		      job.add("lat", c.getLatitude());
		      job.add("lng", c.getLongitude());		  	
		      jab.add(job);
		    }	    
		    return jab.build().toString();
		} else {
			return jab.build().toString();
		}
	}
	
		
	@GET
	@Path("{country}")
	@Produces("application/json")
	public String getCountry(@PathParam("country") String country) {		
		WorldService service = ServiceProvider.getWorldService();	 
		
		switch(country) {
			case "largestsurfaces":						
				JsonArrayBuilder jab = Json.createArrayBuilder();
				for(Country c : service.get10LargestSurfaces()) {
					JsonObjectBuilder job = Json.createObjectBuilder();					
					job.add("code", c.getCode());
					job.add("iso3", c.getIso3Code());
					job.add("name", c.getName());
					job.add("continent", c.getContinent());
					job.add("capital", c.getCapital());
					job.add("region", c.getRegion());
			      	job.add("surfce", c.getSurface());
			      	job.add("population", c.getPopulation());
			      	job.add("government", c.getGovernment());
			      	job.add("lat", c.getLatitude());
			      	job.add("lng", c.getLongitude());		  								
					jab.add(job);
				}
				return jab.build().toString();
			
			case "largestpopulations":
				JsonArrayBuilder jab1 = Json.createArrayBuilder();
				for(Country c : service.get10LargestPopulations()) {
					JsonObjectBuilder job = Json.createObjectBuilder();					
					job.add("code", c.getCode());
					job.add("iso3", c.getIso3Code());
					job.add("name", c.getName());
					job.add("continent", c.getContinent());
					job.add("capital", c.getCapital());
					job.add("region", c.getRegion());
			      	job.add("surfce", c.getSurface());
			      	job.add("population", c.getPopulation());
			      	job.add("government", c.getGovernment());
			      	job.add("lat", c.getLatitude());
			      	job.add("lng", c.getLongitude());		  								
					jab1.add(job);
				}
				return jab1.build().toString();
			
		}


		Country c = service.findByCode(country);	    
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("code", c.getCode());
		job.add("iso3", c.getIso3Code());
		job.add("name", c.getName());
		job.add("continent", c.getContinent());
		job.add("capital", c.getCapital());
		job.add("region", c.getRegion());
      	job.add("surfce", c.getSurface());
      	job.add("population", c.getPopulation());
      	job.add("government", c.getGovernment());
      	job.add("lat", c.getLatitude());
      	job.add("lng", c.getLongitude());	      	
	    return job.build().toString();		
	}
		
	
	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	public void deleteCountry(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		Country country = service.findByCode(code);
		service.delete(country);
	}
	
	
	@PUT
	@RolesAllowed("user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateCountry(@FormParam("name") String name, @FormParam("continent") String continent, @FormParam("region") String region, @FormParam("surfce") double surfce,
			@FormParam("population") int population, @FormParam("government") String government, @FormParam("lat") double lat, @FormParam("lng") double lng, @FormParam("capital") String capital, @FormParam("code") String code) {
		
		WorldService service = ServiceProvider.getWorldService();		
		Country c = service.findByCode(code);		
		Country country = new Country(c.getCode(), c.getIso3Code(), name, capital, continent, region, surfce, population, government, lat, lng);	
		service.update(country);
	}
	
	
	@POST
	@RolesAllowed("user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void saveCountry(@FormParam("name") String name, @FormParam("continent") String continent, @FormParam("region") String region, @FormParam("surfce") double surfce,
			@FormParam("population") int population, @FormParam("government") String government, @FormParam("lat") double lat, @FormParam("lng") double lng, 
			@FormParam("capital") String capital, @FormParam("code") String code, @FormParam("code3") String code3) {
		
		WorldService service = ServiceProvider.getWorldService();				
		Country country = new Country(code, code3, name, capital, continent, region, surfce, population, government, lat, lng);	
		service.save(country);
	}
	
	  

	

}










