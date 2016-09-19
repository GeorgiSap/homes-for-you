package property.building.type;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ConstructionTypeJDBCTemplate implements ConstructionTypeDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(ConstructionType constructionType) {
		String SQL = "insert into construction_types (id, name) values (?, ?)";

		jdbcTemplateObject.update(SQL, constructionType.getId(), constructionType.getName());

		System.out.println("Created ConstructionType Record ID = " + constructionType.getId() + " Name = "
				+ constructionType.getName());
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from construction_types where id = ?";

		jdbcTemplateObject.update(SQL, id);

		System.out.println("Deleted ConstructionType Record with ID = " + id);
	}

	@Override
	public void deleteAll() {
		String SQL = "delete from construction_types";

		jdbcTemplateObject.update(SQL);

		System.out.println("Deleted All ConstructionType Records");
	}

	@Override
	public ConstructionType getConstructionType(int id) {
		String SQL = "select * from construction_types where id = ?";

		ConstructionType constructionType = jdbcTemplateObject.queryForObject(SQL, new Object[] { id },
				new ConstructionTypeMapper());

		return constructionType;
	}

	@Override
	public List<ConstructionType> listConstructionTypes() {
		String SQL = "select * from construction_types";

		List<ConstructionType> constructionTypes = jdbcTemplateObject.query(SQL, new ConstructionTypeMapper());

		return constructionTypes;
	}

	@Override
	public ConstructionType getId(String name) {
		String SQL = "select * from construction_types where name = ?";

		ConstructionType constructionType = jdbcTemplateObject.queryForObject(SQL, new Object[] { name },
				new ConstructionTypeMapper());

		return constructionType;
	}

	@Override
	public void update(int id, String name) {
		String SQL = "update construction_types set name = ? where id = ?";

		jdbcTemplateObject.update(SQL, name, id);

		System.out.println("Updated ConstructionType Record with ID = " + id);
	}

}
