package property.type;

import java.util.List;

import javax.sql.DataSource;

public interface PropertyTypeDAO {

	void setDataSource(DataSource dataSource);

	void create(PropertyType type);

	void delete(int id);

	void deleteAll();

	PropertyType getPropertyType(int id);

	List<PropertyType> listPropertyTypes();

	PropertyType getId(String name);

	void update(int id, String name);

}