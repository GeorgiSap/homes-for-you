package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import user.Guest;

public class GuestMapper implements RowMapper<Guest> {
	public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
		Guest guest = new Guest(rs.getString("email"), 
				rs.getString("phone_number"), 
				rs.getString("user_name"));
		return guest;
	}
}