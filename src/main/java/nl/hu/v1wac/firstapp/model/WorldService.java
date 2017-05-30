package nl.hu.v1wac.firstapp.model;

import java.util.List;
import nl.hu.v1wac.firstapp.persistence.CountryDAO;


public class WorldService extends CountryDAO {		
	
	public List<Country> getAllCountries() {
		return super.findAll();
	}
	
	public List<Country> get10LargestPopulations() {		
		return super.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() {
		return super.find10LargestSurfaces();
	}
	
	public Country getCountryByCode(String code) {
		return super.findByCode(code);
	}
}
