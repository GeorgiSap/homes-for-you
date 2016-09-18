package user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class GuestJDBCTemplate implements GuestDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/* (non-Javadoc)
	 * @see user.GuestDAO#setDataSource(javax.sql.DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see user.GuestDAO#create(user.Guest)
	 */
	@Override
	public long create(Guest guest) {
		String SQL = "insert into guests (email, phone_number, user_name) values (?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });
				pst.setString(1, guest.getEmail());
				pst.setString(2, guest.getPhoneNumber());
				pst.setString(3, guest.getUserName());
				return pst;
			}
		}, keyHolder);
		System.out.println("Created Guest Record ID = " + keyHolder.getKey() + " Name = " + guest.getUserName()
				+ " Phone = " + guest.getPhoneNumber() + " Email = " + guest.getEmail());
		return (long) keyHolder.getKey();
	}

	/* (non-Javadoc)
	 * @see user.GuestDAO#getGuest(int)
	 */
	@Override
	public Guest getGuest(int id) {
		String SQL = "select * from guests where user_id = ?";
		Guest guest = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new GuestMapper());
		return guest;
	}

	/* (non-Javadoc)
	 * @see user.GuestDAO#listGuests()
	 */
	@Override
	public List<Guest> listGuests() {
		String SQL = "select * from guests";
		List<Guest> guests = jdbcTemplateObject.query(SQL, new GuestMapper());
		return guests;
	}

	/* (non-Javadoc)
	 * @see user.GuestDAO#delete(int)
	 */
	@Override
	public void delete(int id) {
		String SQL = "delete from guests where user_id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Guest Record with ID = " + id );
	}

	/* (non-Javadoc)
	 * @see user.GuestDAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		String SQL = "delete from guests";
		jdbcTemplateObject.update(SQL);
		System.out.println("Deleted All Guest Records");
	}

	/* (non-Javadoc)
	 * @see user.GuestDAO#update(user.Guest)
	 */
	@Override
	public void update(Guest user) {
		String SQL = "update guests set email = ?, phone_number = ?, user_name = ? where user_id = ?";
		jdbcTemplateObject.update(SQL, user.getEmail(), user.getPhoneNumber(), user.getUserName(), user.getUserID());
		System.out.println("Updated Record with ID = " + user.getUserID());
	}

}
