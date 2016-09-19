package user.registered;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import user.registered.RegisteredUser;
import user.registered.RegisteredUserMapper;

public class RegisteredUserJDBCTemplate implements RegisteredUserDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#setDataSource(javax.sql.DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}


	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#register(user.registered.RegisteredUser)
	 */
	@Override
	public long register(RegisteredUser user) {
		String SQL = "insert into guests (email, phone_number, user_name) values (?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });
				pst.setString(1, user.getEmail());
				pst.setString(2, user.getPhoneNumber());
				pst.setString(3, user.getUserName());
				return pst;
			}
		}, keyHolder);
		System.out.println("PART1: Created Reg.User Record ID = " + keyHolder.getKey() + " UserName = " + user.getUserName()
				+ " Phone = " + user.getPhoneNumber() + " Email = " + user.getEmail());
		long guestId =  (long) keyHolder.getKey();
		String SQL2 = "insert into registered_users (user_id, first_name, last_name, password) values (?, ?, ?, md5(?))";
		jdbcTemplateObject.update(SQL2, guestId, 
				user.getFirstName(),
				user.getLastName(),
				user.getPassword());
		System.out.println("PART2: Created Reg.User Record ID = " + guestId + " Name = " + user.getFirstName() + " " + user.getLastName());
		return guestId;
	}
	
	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#login(java.lang.String, java.lang.String)
	 */
	@Override
	public void login(String email, String password) throws UserException {
		//RegisteredUser user = getUser(email);
		String SQL = "select * from registered_users r join guests g on (g.user_id = r.user_id) where g.email = ? AND r.password = md5(?)";
		RegisteredUser user = jdbcTemplateObject.queryForObject(SQL, new Object[] { email, password }, new RegisteredUserMapper());
		if (user != null) {
			System.out.println("User with Email " + email +" logged in successfully");
		} else {
			throw new UserException("Wrong Email/Password");
		}
		
	}
	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#getUser(java.lang.String)
	 */
	@Override
	public RegisteredUser getUser(String email) {
		String SQL = "select * from registered_users where email = ?";
		RegisteredUser user = jdbcTemplateObject.queryForObject(SQL, new Object[] { email }, new RegisteredUserMapper());
		return user;
	}
	

	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#getUser(int)
	 */
	@Override
	public RegisteredUser getUser(int id) {
		String SQL = "select * from registered_users r join guests g on (r.user_id = g.user_id) where r.user_id = ?";
		RegisteredUser user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new RegisteredUserMapper());
		return user;
	}


	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#listUsers()
	 */
	@Override
	public List<RegisteredUser> listUsers() {
		String SQL = "select * from registered_users r join guests g on (r.user_id = g.user_id)";
		List<RegisteredUser> registeredUsers = jdbcTemplateObject.query(SQL, new RegisteredUserMapper());
		return registeredUsers;
	}


	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#delete(int)
	 */
	@Override
	public void delete(int id) {
		String SQL = "delete from guests where user_id = ?";
		jdbcTemplateObject.update(SQL, id);
		String SQL2 = "delete from registered_users where user_id = ?";
		jdbcTemplateObject.update(SQL2, id);
		System.out.println("Deleted Reg.User Record with ID = " + id );
	}


	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		String SQL = "delete from guests";
		jdbcTemplateObject.update(SQL);
		String SQL2 = "delete from registered_users";
		jdbcTemplateObject.update(SQL2);
		System.out.println("Deleted All Reg.Users Records");
	}


	/* (non-Javadoc)
	 * @see user.registered.RegisteredUserDAO#update(user.registered.RegisteredUser)
	 */
	@Override
	public void update(RegisteredUser user) {
		String SQL = "update guests set email = ?, phone_number = ?, user_name = ? where user_id = ?";
		jdbcTemplateObject.update(SQL, user.getEmail(), user.getPhoneNumber(), user.getUserName(), user.getUserID());
		System.out.println("PART1: Updated Reg.User Record with ID = " + user.getUserID());
		
		String SQL2 = "update registered_users set first_name = ?, last_name = ?, password = md5(?) where user_id = ?";
		jdbcTemplateObject.update(SQL2, user.getFirstName(), user.getLastName(), user.getPassword(), user.getUserID());
		System.out.println("PART2: Updated Reg.User with ID = " + user.getUserID());
	}
}
