package user;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.registered.RegisteredUser;
import user.registered.RegisteredUserDAO;
import user.registered.UserException;

public class UserTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RegisteredUserDAO userJDBCTemplate = (RegisteredUserDAO) context.getBean("registeredUserJDBCTemplate");

		System.out.println("------Records Deletion--------");
		userJDBCTemplate.deleteAll();

		System.out.println("------Records Creation--------");
		long userId = (long) userJDBCTemplate.register(
				new RegisteredUser(0, "georgi@gmail.com", "0899999999", "Gosho", "Ivanov", "parola", "Gosho2016"));
		userJDBCTemplate
				.register(new RegisteredUser(0, "pesho@gmail.com", "0899555555", "Petar", "Petrov", "1234", "Pesho87"));
		userJDBCTemplate
				.register(new RegisteredUser(0, "ivan@gmail.com", "0899777777", "Ivan", "Ivanov", "marola", "Ivan95"));

		System.out.println("------Listing Multiple Records--------");
		List<RegisteredUser> users = userJDBCTemplate.listUsers();
		for (RegisteredUser user : users) {
			System.out.print("EMail : " + user.getEmail());
			System.out.print(", Phone : " + user.getPhoneNumber());
			System.out.print(", UserName : " + user.getUserName());
			System.out.print(", FirstName : " + user.getFirstName());
			System.out.print(", LastName : " + user.getLastName());
			System.out.println(", Password : " + user.getPassword());
		}

		System.out.println("----Updating Record with ID = " + userId + " -----");
		userJDBCTemplate.update(new RegisteredUser((int) userId, "georgi@gmail.com", "0899999999", "Gosho", "Ivanov",
				"parola", "Gosho88"));

		System.out.println("----Listing Record with ID = " + userId + " -----");
		RegisteredUser user = userJDBCTemplate.getUser((int) userId);
		System.out.print("EMail : " + user.getEmail());
		System.out.print(", Phone : " + user.getPhoneNumber());
		System.out.print(", UserName : " + user.getUserName());
		System.out.print(", FirstName : " + user.getFirstName());
		System.out.print(", LastName : " + user.getLastName());
		System.out.println(", Password : " + user.getPassword());
		System.out.println("----Trying to Login Reg./Unreg. Users -----");
		try {
			userJDBCTemplate.login("georgi@gmail.com", "parola");
			userJDBCTemplate.login("pesho@gmail.com", "1234");
			userJDBCTemplate.login("ivan@gmail.com", "marola");
		} catch (UserException e) {
			System.out.println("Wrong email/password");
			e.printStackTrace();
		}

	}

}
