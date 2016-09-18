package property.parcel;

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

public class ParcelJDBCTemplate implements ParcelDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public long createParcel(Parcel parcel) {

		String SQL = "insert into properties (city_id, address, price, area, creation_date, property_type_id, user_id, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });
				pst.setInt(1, parcel.getCityId());
				pst.setString(2, parcel.getAddress());
				pst.setDouble(3, parcel.getPrice());
				pst.setDouble(4, parcel.getArea());
				pst.setDate(5, new Date(parcel.getCreationDate().getTime()));
				pst.setInt(6, parcel.getPropertyTypeId());
				pst.setInt(7, parcel.getUserId());
				pst.setString(8, parcel.getDescription());
				return pst;
			}
		}, keyHolder);

		long listingId = (long) keyHolder.getKey();
		String SQL2 = "insert into parcels (listing_id, is_regulated, has_electricity, has_water) values (?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL2, listingId, parcel.isRegulated(), parcel.hasElectricity(), parcel.hasWater());
		System.out.println("Created Parcel Record ID = " + keyHolder.getKey() + " Date = " + parcel.getCreationDate());
		return listingId;
	}

	@Override
	public void updateParcel(Parcel parcel) {

		String SQL = "update properties set city_id = ?, address = ?, price = ?, area = ?, creation_date = ?, property_type_id = ?, "
				+ "user_id = ?, description = ? where id = ?";
		jdbcTemplateObject.update(SQL, parcel.getCityId(), parcel.getAddress(), parcel.getPrice(), parcel.getArea(),
				parcel.getCreationDate(), parcel.getPropertyTypeId(), parcel.getListingID());

		String SQL2 = "update parcels is_regulated = ? has_electricity = ? has_water = ? where id = ?";
		jdbcTemplateObject.update(SQL2, parcel.isRegulated(), parcel.hasElectricity(), parcel.hasWater(),
				parcel.getListingID());
	}

	@Override
	public List<Parcel> listParcels() {
		String SQL = "select * from properties pr join parcels pc on (pr.listing_id = pc.listing_id)";
		List<Parcel> parcels = jdbcTemplateObject.query(SQL, new ParcelMapper());
		return parcels;
	}

	@Override
	public void delete(int id) {
		String SQL = "delete from properties where listing_id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Property Record with ID = " + id);

		String SQL2 = "delete from parcels where listing_id = ?";
		jdbcTemplateObject.update(SQL2, id);
		System.out.println("Deleted Parcel Record with ID = " + id);
	}

	@Override
	public void deleteAll() {
		List<Parcel> parcels = listParcels();
		for (Parcel parcel : parcels) {
			delete(parcel.getListingID());
		}
	}

}
