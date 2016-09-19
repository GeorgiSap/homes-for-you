package user.registered;
import java.util.HashSet;
import java.util.Set;

import property.main.Property;
import user.Guest;

public class RegisteredUser extends Guest{
	
	private String firstName;
	private String lastName;
	private String password;
	private Set<Property> userProperties;

	public RegisteredUser(int userId, String email, String phoneNumber,
			String firstName, String lastName,
			String password, String userName) {
		super(userId, email, phoneNumber, userName);
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		userProperties = new HashSet<Property>();
		
	}
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	class Password {
		
	}
	
	public void addProperty(Property property) {
		userProperties.add(property);
	}
	
	public void removeProperty(Property property) {
		userProperties.remove(property);
	}
	
	public void updateProperty(Property property) {
		
	}
	
	
}
