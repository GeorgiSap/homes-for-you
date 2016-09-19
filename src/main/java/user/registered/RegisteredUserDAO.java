package user.registered;

import java.util.List;

import javax.sql.DataSource;

public interface RegisteredUserDAO {

	void setDataSource(DataSource dataSource);

	long register(RegisteredUser user);

	void login(String email, String password) throws UserException;

	RegisteredUser getUser(String email);

	RegisteredUser getUser(int id);

	List<RegisteredUser> listUsers();

	void delete(int id);

	void deleteAll();

	void update(RegisteredUser user);

}