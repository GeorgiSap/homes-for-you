package property.city;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import user.Guest;

public class CityMapper implements RowMapper<City> {
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		City city = new City(
				rs.getInt("id"), 
				rs.getString("name"));
		return city;
	}
}