package property.land.type;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LandTypeMapper implements RowMapper<LandType> {
	public LandType mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		LandType landType = new LandType(
				rs.getInt("id"), 
				rs.getString("name"));
		
		return landType;
	}
}
