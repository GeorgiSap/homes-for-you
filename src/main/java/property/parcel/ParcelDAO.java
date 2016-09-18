package property.parcel;

import java.util.List;

import javax.sql.DataSource;

public interface ParcelDAO {

	void setDataSource(DataSource dataSource);

	long createParcel(Parcel parcel);

	void updateParcel(Parcel parcel);

	List<Parcel> listParcels();

	void delete(int id);

	void deleteAll();

}