package property.city;

import java.util.List;

import javax.sql.DataSource;

public interface CityDAO {

	void setDataSource(DataSource dataSource);

	void create(City city);

	City getCity(int id);

	City getId(String name);

	List<City> listCities();

	void delete(int id);

	void deleteAll();

	void update(int id, String name);

}