package property.land.type;

import java.util.List;

import javax.sql.DataSource;

public interface LandTypeDAO {

	void setDataSource(DataSource dataSource);

	void create(LandType landType);

	void delete(int id);

	void deleteAll();

	LandType getLandType(int id);

	List<LandType> listLandTypes();

	LandType getId(String name);

	void update(int id, String name);
}
