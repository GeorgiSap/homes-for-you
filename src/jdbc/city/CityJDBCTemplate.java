package jdbc.city;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;


public class CityJDBCTemplate implements CityDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	@Override
	public void create(int id, String name) {
	      String SQL = "insert into cities (id, name) values (?, ?)";
	      jdbcTemplateObject.update( SQL, id, name);
	      System.out.println("Created Record ID = " + id + " Name = " + name );
	      return;
	}

	@Override
	public City getCity(int id) {
		String SQL = "select * from cities where id = ?";
		City city = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new CityMapper());
	      return city;
	}

	@Override
	public List<City> listCities() {
	      String SQL = "select * from cities";
	      List <City> cities = jdbcTemplateObject.query(SQL, 
	                                new CityMapper());
	      return cities;
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from cities where id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      System.out.println("Deleted Record with ID = " + id );
	      return;
	}
	
	public void deleteAll() {
		String SQL = "delete from cities";
	      jdbcTemplateObject.update(SQL);
	      System.out.println("Deleted All Records");
	}

	@Override
	public void update(int id, String name) {
		 String SQL = "update cities set name = ? where id = ?";
	      jdbcTemplateObject.update(SQL, name, id);
	      System.out.println("Updated Record with ID = " + id );
	      return;

	}

}
