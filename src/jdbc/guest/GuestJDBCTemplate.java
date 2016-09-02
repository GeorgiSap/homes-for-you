package jdbc.guest;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import user.Guest;

public class GuestJDBCTemplate implements GuestDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	@Override
	public void create(String email, String phoneNumber, String userName) {
	      String SQL = "insert into guests (email, phone_number, user_name) values (?, ?, ?)";
	      jdbcTemplateObject.update( SQL, email, phoneNumber, userName);
	      System.out.println("Created Record Email = " + email + " Phone Number = " + phoneNumber + " User Name = " + userName);
	      return;
	}

	@Override
	public Guest getGuest(String email) {
		String SQL = "select * from guests where email = ?";
		Guest guest = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{email}, new GuestMapper());
	      return guest;
	}

	@Override
	public List<Guest> listGuests() {
	      String SQL = "select * from guests";
	      List <Guest> guests = jdbcTemplateObject.query(SQL, 
	                                new GuestMapper());
	      return guests;
	}

	@Override
	public void delete(String email) {
		String SQL = "delete from guests where email = ?";
	      jdbcTemplateObject.update(SQL, email);
	      System.out.println("Deleted Record with EMail = " + email );
	      return;
	}
	
	public void deleteAll() {
		String SQL = "delete from guests";
	      jdbcTemplateObject.update(SQL);
	      System.out.println("Deleted All Records");
	}

	@Override
	public void update(String email, String phoneNumber) {
		 String SQL = "update guests set phone_number = ? where email = ?";
	      jdbcTemplateObject.update(SQL, phoneNumber, email);
	      System.out.println("Updated Record with EMail = " + email );
	      return;

	}

}
