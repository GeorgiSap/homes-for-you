package property.land.type;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class LandTypeJDBCTemplate implements LandTypeDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(LandType landType) {
		String SQL = "insert into land_types (id, name) values (?, ?)";

		jdbcTemplateObject.update(SQL, landType.getId(), landType.getName());

		System.out.println("Created LandType Record ID = " + landType.getId() + " Name = " + landType.getName());
	}

	@Override
	public LandType getLandType(int id) {
		String SQL = "select * from land_types where id = ?";

		LandType landType = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new LandTypeMapper());

		return landType;
	}

	@Override
	public LandType getId(String name) {
		String SQL = "select * from land_types where name = ?";

		LandType landType = jdbcTemplateObject.queryForObject(SQL, new Object[] { name }, new LandTypeMapper());

		return landType;
	}

	@Override
	public List<LandType> listLandTypes() {
		String SQL = "select * from land_types";

		List<LandType> landTypes = jdbcTemplateObject.query(SQL, new LandTypeMapper());

		return landTypes;
	}

	@Override
	public void update(int id, String name) {
		String SQL = "update land_types set name = ? where id = ?";

		jdbcTemplateObject.update(SQL, name, id);

		System.out.println("Updated LandType Record with ID = " + id);
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from land_types where id = ?";

		jdbcTemplateObject.update(SQL, id);

		System.out.println("Deleted LandType Record with ID = " + id);
	}

	@Override
	public void deleteAll() {
		String SQL = "delete from land_types";

		jdbcTemplateObject.update(SQL);

		System.out.println("Deleted All LandType Records");
	}

}
