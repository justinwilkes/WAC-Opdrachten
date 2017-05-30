package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import nl.hu.v1wac.firstapp.model.Country;


public class CountryDAO extends BaseDAO {
	
	
	public Country save(Country country) {
		Connection connection = super.getConnection();	
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO country VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");			
			if(statement != null) {
				statement.setString(1, country.getIso3Code());
				statement.setString(2, country.getName());
				statement.setString(3, country.getContinent());
				statement.setString(4, country.getRegion());
				statement.setDouble(5, country.getSurface());
				statement.setInt(6, 0);
				statement.setDouble(7, country.getPopulation());
				statement.setInt(8, 0);
				statement.setInt(9, 0);
				statement.setInt(10, 0);
				statement.setInt(11, 0);
				statement.setString(12, country.getGovernment());
				statement.setInt(13, 0);
				statement.setString(14, country.getCode());
				statement.setDouble(15, country.getLatitude());
				statement.setDouble(16, country.getLongitude());
				statement.setString(17, country.getCapital());

				try {
					statement.executeUpdate();					
				} catch(Exception ex) {	ex.printStackTrace();	}
				statement.close();
			}
		} catch (Exception ex) { ex.printStackTrace(); 	}
		
		return findByCode(country.getCode());
	}
	
	
	
	public List<Country> findAll() {
		Connection connection = super.getConnection();	
		List<Country> results = new ArrayList<Country>();		
		try {			
			Statement stmt = connection.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("SELECT * FROM country ORDER BY name");
			while (dbResultSet.next()) {
				String iso2cd = dbResultSet.getString("code2");
				String iso3cd = dbResultSet.getString("code");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double lat = dbResultSet.getDouble("latitude");
				double lon = dbResultSet.getDouble("longitude");
				Country country = new Country(iso2cd, iso3cd, name, capital, continent, region, surface, population, government, lat, lon);
				results.add(country);
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		return results;
	}
	
	
	
	public Country findByCode(String code) {		
		List<Country> countries = new ArrayList<Country>(findAll());		
		for (Country c : countries) {
			if (c.getCode().equals(code)) return c;			
		}
		return null;
	}
	
	
	
	public List<Country> find10LargestPopulations() {
		List<Country> countries = new ArrayList<Country>(findAll());		
		Collections.sort(countries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getPopulation() - c1.getPopulation());
			}
		});	
		return countries.subList(0, 10);
	}

	
	
	public List<Country> find10LargestSurfaces() {
		List<Country> countries = new ArrayList<Country>(findAll());		
		Collections.sort(countries, new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getSurface() - c1.getSurface());
			}
		});	
		return countries.subList(0, 10);
	}
	
	
	
	public Country update(Country country) {
		Connection connection = super.getConnection();		
		System.out.println(country.getContinent());
		try {		
		PreparedStatement statement = connection.prepareStatement("UPDATE country SET name=?, continent=?::continenttype, region=?, surfacearea=?, population=?, governmentform=?, code2=?, latitude=?, longitude=?, capital=?  WHERE code=?");
		statement.setString(1, country.getName());
		statement.setString(2, country.getContinent());			
		statement.setString(3, country.getRegion());
		statement.setDouble(4, country.getSurface());
		statement.setInt(5, country.getPopulation());
		statement.setString(6, country.getGovernment());
		statement.setString(7, country.getCode());
		statement.setDouble(8, country.getLatitude());
		statement.setDouble(9, country.getLongitude());
		statement.setString(10, country.getCapital());
		statement.setString(11, country.getIso3Code());		
		statement.executeUpdate();
		statement.close();		
		} catch (SQLException ex) {	ex.printStackTrace(); }		
		return findByCode(country.getCode());
	}
	
	
	
	public boolean delete(Country country) {
		Connection connection = super.getConnection();		
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM country WHERE code=?");
			statement.setString(1, country.getIso3Code());
			statement.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException ex) { ex.printStackTrace();	}
		return false;
	}
}
