package user.registered;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RegisteredUserMapper implements RowMapper<RegisteredUser> {
	public RegisteredUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegisteredUser user = new RegisteredUser(
				rs.getInt("user_id"),
				rs.getString("email"), 
				rs.getString("phone_number"), 
				rs.getString("first_name"), 
				rs.getString("last_name"), 
				rs.getString("password"),
				rs.getString("user_name"));
		return user;
	}
}