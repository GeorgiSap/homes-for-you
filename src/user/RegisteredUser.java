package user;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import agency.Agency;
import agency.SortCriteria;
import property.Property;

public class RegisteredUser extends Guest{
	
	private String firstName;
	private String lastName;
	private String password;
	private Set<Property> userProperties;

	public RegisteredUser(String email, String phoneNumber, String firstName, String lastName,
			String password) {
		super(email, phoneNumber);
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		userProperties = new HashSet<Property>();
		
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
