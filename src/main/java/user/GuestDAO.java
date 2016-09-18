package user;

import java.util.List;

import javax.sql.DataSource;

public interface GuestDAO {

	void setDataSource(DataSource dataSource);

	long create(Guest guest);

	Guest getGuest(int id);

	List<Guest> listGuests();

	void delete(int id);

	void deleteAll();

	void update(Guest user);

}