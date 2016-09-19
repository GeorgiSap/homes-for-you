package property.land;

import java.util.List;

import javax.sql.DataSource;

public interface LandDAO {

	void setDataSource(DataSource dataSource);

	long createLand(Land land);

	void updateLand(Land land);

	List<Land> listLands();

	void delete(int id);

	void deleteAll();

}
