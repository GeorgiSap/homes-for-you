package property.land;

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

public class LandJDBCTemplate implements LandDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

	@Override
	public long createLand(Land land) {

		String SQL = "insert into properties (city_id, address, price, area, creation_date, property_type_id, user_id, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });

				pst.setInt(1, land.getCityId());
				pst.setString(2, land.getAddress());
				pst.setDouble(3, land.getPrice());
				pst.setDouble(4, land.getArea());
				pst.setDate(5, new Date(land.getCreationDate().getTime()));
				pst.setInt(6, land.getPropertyTypeId());
				pst.setInt(7, land.getUserId());
				pst.setString(8, land.getDescription());

				return pst;
			}
		}, keyHolder);

		long listingId = (long) keyHolder.getKey();

		String SQL2 = "insert into lands (listing_id, land_type_id, category) values (?, ?, ?)";

		jdbcTemplateObject.update(SQL2, listingId, land.getLandTypeId(), land.getCategory());

		System.out.println("Created Land Record ID = " + keyHolder.getKey() + " Date = " + land.getCreationDate());

		return listingId;
	}

	@Override
	public void updateLand(Land land) {

		String SQL = "update properties set city_id = ?, address = ?, price = ?, area = ?, creation_date = ?, property_type_id = ?, "
				+ "user_id = ?, description = ? where listing_id = ?";

		jdbcTemplateObject.update(SQL, land.getCityId(), land.getAddress(), land.getPrice(), land.getArea(),
				land.getCreationDate(), land.getPropertyTypeId(), land.getListingID());

		String SQL2 = "update lands set land_type_id = ?, category = ? where listing_id = ?";

		jdbcTemplateObject.update(SQL2, land.getLandTypeId(), land.getCategory(), land.getListingID());
	}

	@Override
	public List<Land> listLands() {
		String SQL = "select * from properties pr join lands ln on (pr.listing_id = ln.listing_id)";

		List<Land> lands = jdbcTemplateObject.query(SQL, new LandMapper());

		return lands;
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from properties where listing_id = ?";

		jdbcTemplateObject.update(SQL, id);

		System.out.println("Deleted Property Record with ID = " + id);

		String SQL2 = "delete from lands where listing_id = ?";

		jdbcTemplateObject.update(SQL2, id);

		System.out.println("Deleted Land Record with ID = " + id);

	}

	@Override
	public void deleteAll() {
		List<Land> lands = listLands();

		for (Land land : lands) {
			delete(land.getListingID());
		}
	}

}
