package user;

public class Admin extends RegisteredUser{

	public Admin(String email, String phoneNumber, String firstName, String lastName, String password, String userName) {
		super(email, phoneNumber, firstName, lastName, password, userName);
	}
}
