package property;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import property.main.PropertyException;
import property.land.Land;
import property.land.LandDAO;
import user.Guest;
import user.GuestDAO;

public class LandGuestTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	LandDAO landJDBCTemplate = (LandDAO) context.getBean("landJDBCTemplate");
	GuestDAO guestJDBCTemplate = (GuestDAO) context.getBean("guestJDBCTemplate");

	@Test
	public void test() throws PropertyException {
		
		landJDBCTemplate.deleteAll();
		guestJDBCTemplate.deleteAll();

		Guest user = new Guest(0, "peshofasa2@gmail.com", "0877666555", "pesho_fasa");
		long userID = guestJDBCTemplate.create(user);
		System.out.println("User s id : " + userID);
		
		landJDBCTemplate.createLand(new Land(0, 1, "Centar", 200000, 200000000, new Date() ,(int)userID, 1, 3, "Geto na imigranti"));
	}

}

