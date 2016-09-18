package property;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.Guest;
import user.GuestDAO;

public class GuestTest {

	@Test
	public void test() {
		 ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");
	      GuestDAO guestJDBCTemplate = 
	      (GuestDAO)context.getBean("guestJDBCTemplate");
	      
	      System.out.println("------Records Deletion--------" );
	      guestJDBCTemplate.deleteAll();
	      
	      
	      System.out.println("------Records Creation--------" );
	      int userId = (int)guestJDBCTemplate.create(new Guest(0, "gosho@gmail.com", "0899999999", "Gosho"));
	      guestJDBCTemplate.create(new Guest(0, "pesho@gmail.com", "0899555555", "Pesho"));
	      guestJDBCTemplate.create(new Guest(0, "ivan@gmail.com", "0899777777", "Ivan"));

	      System.out.println("------Listing Multiple Records--------" );
	      List<Guest> guests = guestJDBCTemplate.listGuests();
	      for (Guest record : guests) {
	         System.out.print("EMail : " + record.getEmail() );
	         System.out.print(", Phone : " + record.getPhoneNumber() );
	         System.out.println(", User Name : " + record.getUserName());
	      }

	      System.out.println("----Updating Record with ID = " + userId + " -----" );
	      guestJDBCTemplate.update(new Guest(userId, "georgi@gmail.com", "0899999999", "Gosho"));

	      System.out.println("----Listing Record with ID = " + userId + " -----" );
	      Guest guest = guestJDBCTemplate.getGuest(userId);
	      System.out.print("EMail : " + guest.getEmail() );
	      System.out.print(", Phone : " + guest.getPhoneNumber() );
	      System.out.println(", User Name : " + guest.getUserName());
	}

}
