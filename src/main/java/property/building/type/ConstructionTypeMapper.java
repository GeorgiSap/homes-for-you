package property.building.type;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ConstructionTypeMapper implements RowMapper<ConstructionType> {
	public ConstructionType mapRow(ResultSet rs, int rowNum) throws SQLException {

		ConstructionType constructionType = new ConstructionType(
				rs.getInt("id"), 
				rs.getString("name"));

		return constructionType;
	}
}
