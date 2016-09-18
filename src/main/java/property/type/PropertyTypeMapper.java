package property.type;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PropertyTypeMapper implements RowMapper<PropertyType> {
	public PropertyType mapRow(ResultSet rs, int rowNum) throws SQLException {
		PropertyType type = new PropertyType(
				rs.getInt("id"), 
				rs.getString("name"));
		return type;
	}
}
