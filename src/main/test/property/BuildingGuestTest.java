package property;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import property.building.Building;
import property.building.BuildingDAO;
import property.main.PropertyException;
import user.Guest;
import user.GuestDAO;

public class BuildingGuestTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	BuildingDAO buildingJDBCTemplate = (BuildingDAO) context.getBean("buildingJDBCTemplate");
	GuestDAO guestJDBCTemplate = (GuestDAO) context.getBean("guestJDBCTemplate");

	@Test
	public void test() throws PropertyException {
		
		buildingJDBCTemplate.deleteAll();
		guestJDBCTemplate.deleteAll();

		Guest user = new Guest(0, "peshofasa2@gmail.com", "0877666555", "pesho_fasa");
		long userID = guestJDBCTemplate.create(user);
		System.out.println("User s id : " + userID);
		
		buildingJDBCTemplate.createBuilding(new Building(0, 1, "Centar", 200000, 200000000, new Date() ,(int)userID, 1, 5, 1, true, false, "Geto na imigranti"));
	}

}
