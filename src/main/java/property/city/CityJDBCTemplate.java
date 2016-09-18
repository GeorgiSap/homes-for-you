package property.city;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class CityJDBCTemplate implements CityDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	   /* (non-Javadoc)
	 * @see property.city.CityDAO#setDataSource(javax.sql.DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	
	/* (non-Javadoc)
	 * @see property.city.CityDAO#create(property.city.City)
	 */
	@Override
	public void create(City city) {
		 String SQL = "insert into cities (id, name) values (?, ?)";
		 jdbcTemplateObject.update( SQL, city.getId(), city.getName());
		 System.out.println("Created City Record ID = " + city.getId() + " Name = " + city.getName());
	}

	/* (non-Javadoc)
	 * @see property.city.CityDAO#getCity(int)
	 */
	@Override
	public City getCity(int id) {
		String SQL = "select * from cities where id = ?";
		City city = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new CityMapper());
	      return city;
	}
	
	/* (non-Javadoc)
	 * @see property.city.CityDAO#getId(java.lang.String)
	 */
	@Override
	public City getId(String name) {
		String SQL = "select * from cities where name = ?";
		City city = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{name}, new CityMapper());
	      return city;
	}
	
	/* (non-Javadoc)
	 * @see property.city.CityDAO#listCities()
	 */
	@Override
	public List<City> listCities() {
	      String SQL = "select * from cities";
	      List <City> cities = jdbcTemplateObject.query(SQL, 
	                                new CityMapper());
	      return cities;
	}

	/* (non-Javadoc)
	 * @see property.city.CityDAO#delete(int)
	 */
	@Override
	public void delete(int id) {
		String SQL = "delete from cities where id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      System.out.println("Deleted City Record with ID = " + id );
	}
	
	/* (non-Javadoc)
	 * @see property.city.CityDAO#deleteAll()
	 */
	@Override
	public void deleteAll() {
		String SQL = "delete from cities";
	      jdbcTemplateObject.update(SQL);
	      System.out.println("Deleted All City Records");
	}

	/* (non-Javadoc)
	 * @see property.city.CityDAO#update(int, java.lang.String)
	 */
	@Override
	public void update(int id, String name) {
		 String SQL = "update cities set name = ? where id = ?";
	      jdbcTemplateObject.update(SQL, name, id);
	      System.out.println("Updated Record with ID = " + id );
	      return;

	}

}
