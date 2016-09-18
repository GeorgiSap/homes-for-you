package property;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import property.main.PropertyException;
import property.parcel.Parcel;
import property.parcel.ParcelDAO;
import user.Guest;
import user.GuestDAO;

public class ParcelGuestTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	ParcelDAO parcelJDBCTemplate = (ParcelDAO) context.getBean("parcelJDBCTemplate");
	GuestDAO guestJDBCTemplate = (GuestDAO) context.getBean("guestJDBCTemplate");

	@Test
	public void test() throws PropertyException {
		
		parcelJDBCTemplate.deleteAll();
		guestJDBCTemplate.deleteAll();

		Guest user = new Guest(0, "peshofasa2@gmail.com", "0877666555", "pesho_fasa");
		long userID = guestJDBCTemplate.create(user);
		System.out.println("User s id : " + userID);
		
		parcelJDBCTemplate.createParcel(new Parcel(0, 1, "Centar", 200000, 200000000, new Date() ,(int)userID, false, false, false, "Geto na imigranti"));
	}

}
