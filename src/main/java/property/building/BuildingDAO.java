package property.building;

import java.util.List;

import javax.sql.DataSource;

public interface BuildingDAO {

	void setDataSource(DataSource dataSource);

	long createBuilding(Building building);

	void updateBuilding(Building building);

	List<Building> listBuildings();

	void delete(int id);

	void deleteAll();

}
