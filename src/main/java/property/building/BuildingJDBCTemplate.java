package property.building;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class BuildingJDBCTemplate implements BuildingDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

	@Override
	public long createBuilding(Building building) {
		String SQL = "insert into properties (city_id, address, price, area, creation_date, property_type_id, user_id, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });

				pst.setInt(1, building.getCityId());
				pst.setString(2, building.getAddress());
				pst.setDouble(3, building.getPrice());
				pst.setDouble(4, building.getArea());
				pst.setDate(5, new Date(building.getCreationDate().getTime()));
				pst.setInt(6, building.getPropertyTypeId());
				pst.setInt(7, building.getUserId());
				pst.setString(8, building.getDescription());

				return pst;
			}
		}, keyHolder);

		long listingId = (long) keyHolder.getKey();

		String SQL2 = "insert into buildings (listing_id, floor, construction_type_id, has_tec, is_furnished) values (?, ?, ?, ?, ?)";

		jdbcTemplateObject.update(SQL2, listingId, building.getFloor(), building.getContructionTypeId(),
				building.hasTEC(), building.isFurnished());

		System.out.println(
				"Created Building Record ID = " + keyHolder.getKey() + " Date = " + building.getCreationDate());

		return listingId;
	}

	@Override
	public void updateBuilding(Building building) {

		String SQL = "update properties set city_id = ?, address = ?, price = ?, area = ?, creation_date = ?, property_type_id = ?, "
				+ "user_id = ?, description = ? where listing_id = ?";

		jdbcTemplateObject.update(SQL, building.getCityId(), building.getAddress(), building.getPrice(),
				building.getArea(), building.getCreationDate(), building.getPropertyTypeId(), building.getListingID());

		String SQL2 = "update buildings set floor = ?, construction_type_id = ?, has_tec = ?, is_furnished = ? where listing_id = ?";

		jdbcTemplateObject.update(SQL2, building.getFloor(), building.getContructionTypeId(), building.hasTEC(),
				building.isFurnished(), building.getListingID());

	}

	@Override
	public List<Building> listBuildings() {
		String SQL = "select * from properties pr join buildings bd on (pr.listing_id = bd.listing_id)";

		List<Building> buildings = jdbcTemplateObject.query(SQL, new BuildingMapper());

		return buildings;
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from properties where listing_id = ?";

		jdbcTemplateObject.update(SQL, id);

		System.out.println("Deleted Property Record with ID = " + id);

		String SQL2 = "delete from buildings where listing_id = ?";

		jdbcTemplateObject.update(SQL2, id);

		System.out.println("Deleted Building Record with ID = " + id);

	}

	@Override
	public void deleteAll() {
		List<Building> buildings = listBuildings();

		for (Building building : buildings) {
			delete(building.getListingID());
		}
	}

}
