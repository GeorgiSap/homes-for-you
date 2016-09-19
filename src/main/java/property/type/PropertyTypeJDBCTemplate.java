package property.type;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class PropertyTypeJDBCTemplate implements PropertyTypeDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void create(PropertyType propertyType) {
		 String SQL = "insert into property_types (id, name) values (?, ?)";
		 jdbcTemplateObject.update( SQL, propertyType.getId(), propertyType.getName());
		 System.out.println("Created PropertyType Record ID = " + propertyType.getId() + " Name = " + propertyType.getName());
	}

	@Override
	public PropertyType getPropertyType(int id) {
		String SQL = "select * from property_types where id = ?";
		PropertyType propertyType = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new PropertyTypeMapper());
	      return propertyType;
	}

	@Override
	public PropertyType getId(String name) {
		String SQL = "select * from property_types where name = ?";
		PropertyType propertyType = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{name}, new PropertyTypeMapper());
	      return propertyType;
	}

	@Override
	public List<PropertyType> listPropertyTypes() {
	      String SQL = "select * from property_types";
	      List <PropertyType> types = jdbcTemplateObject.query(SQL, 
	                                new PropertyTypeMapper());
	      return types;
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from property_types where id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      System.out.println("Deleted PropertyType Record with ID = " + id );
	}

	@Override
	public void deleteAll() {
		String SQL = "delete from property_types";
	      jdbcTemplateObject.update(SQL);
	      System.out.println("Deleted All PropertyType Records");
	}

	@Override
	public void update(int id, String name) {
		 String SQL = "update property_types set name = ? where id = ?";
	      jdbcTemplateObject.update(SQL, name, id);
	      System.out.println("Updated PropertyType Record with ID = " + id );
	}

}
