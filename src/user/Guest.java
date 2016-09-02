package user;

public class Guest {
	private String email;
	private String phoneNumber;
	private String userName;
	
	public Guest(String email, String phoneNumber, String userName) {	
		setEmail(email);
		setPhoneNumber(phoneNumber);
		setUserName(userName);
	}
	
	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Guest [email=" + email + ", phoneNumber=" + phoneNumber + ", userName=" + userName + "]";
	}

}
