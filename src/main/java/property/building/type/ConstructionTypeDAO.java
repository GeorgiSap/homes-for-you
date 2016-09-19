package property.building.type;

import java.util.List;

import javax.sql.DataSource;

public interface ConstructionTypeDAO {
	void setDataSource(DataSource dataSource);

	void create(ConstructionType constructionType);

	void delete(int id);

	void deleteAll();

	ConstructionType getConstructionType(int id);

	List<ConstructionType> listConstructionTypes();

	ConstructionType getId(String name);

	void update(int id, String name);
}
