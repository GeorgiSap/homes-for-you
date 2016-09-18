package property.parcel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import property.main.PropertyException;

public class ParcelMapper implements RowMapper<Parcel> {
	public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
		Parcel parcel = null;
		try {
			parcel = new Parcel(
					rs.getInt("listing_id"),
					rs.getInt("city_id"),
					rs.getString("address"), 
					rs.getInt("price"), 
					rs.getInt("area"), 
					rs.getDate("creation_date"),
					rs.getInt("user_id"),
					rs.getBoolean(10),
					rs.getBoolean(11),
					rs.getBoolean(12),
					rs.getString("description"));
		} catch (PropertyException e) {
			e.printStackTrace();
		}
		return parcel;
	}
	
}


