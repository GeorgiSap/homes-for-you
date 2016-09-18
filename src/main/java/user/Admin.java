package user;

public class Admin extends RegisteredUser{

	public Admin(int userId, String email, String phoneNumber, String firstName, String lastName, String password, String userName) {
		super(userId, email, phoneNumber, firstName, lastName, password, userName);
	}
}
