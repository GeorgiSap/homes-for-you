package property.building;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import property.main.PropertyException;

public class BuildingMapper implements RowMapper<Building>{
	
	@Override
	public Building mapRow(ResultSet rs, int rowNum) throws SQLException {
		Building building = null;
		
		try {
			
			building = new Building(
					rs.getInt("listing_id"),
					rs.getInt("city_id"),
					rs.getString("address"), 
					rs.getInt("price"), 
					rs.getInt("area"), 
					rs.getDate("creation_date"),
					rs.getInt("user_id"),
					rs.getInt("property_type_id"),
					rs.getInt("floor"),
					rs.getInt("construction_type_id"),
					rs.getBoolean("has_tec"),
					rs.getBoolean("is_furnished"),
					rs.getString("description"));
			
		} catch (PropertyException e) {
			e.printStackTrace();
		}
		
		return building;
	}

}
