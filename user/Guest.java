package user;

public class Guest {
	private String email;
	private String phoneNumber;
	
	public Guest(String email, String phoneNumber) {	
		this.email = email;
		this.phoneNumber = phoneNumber;
		
	}
	
	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return getClass() + " [email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
}
