package property.land;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import property.main.PropertyException;

public class LandMapper implements RowMapper<Land> {
	public Land mapRow(ResultSet rs, int rowNum) throws SQLException {
		Land land = null;
		
		try {
			
			land = new Land(
					rs.getInt("listing_id"),
					rs.getInt("city_id"),
					rs.getString("address"), 
					rs.getInt("price"), 
					rs.getInt("area"), 
					rs.getDate("creation_date"),
					rs.getInt("user_id"),
					rs.getInt("land_type_id"),
					rs.getInt("category"),
					rs.getString("description"));
			
		} catch (PropertyException e) {
			e.printStackTrace();
		}
		
		return land;
	}
}
