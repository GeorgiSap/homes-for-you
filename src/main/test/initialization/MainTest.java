package initialization;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import agency.Agency;
import agency.SortCriteria;
import property.building.Building;
import property.city.City;
import property.main.Property;
import property.main.PropertyException;
import property.type.PropertyType;
import user.Admin;
import user.Guest;
import user.RegisteredUser;

public class MainTest {

	@Test
	public void test() {
		Agency homes4You = new Agency();
		String[] cities = {"SOFIA", "PLOVDIV", "VARNA", "BURGAS", "RUSE"};
		String[] typeArr = {"HOUSE", "OFFICE", "GARAGE", 
				"MANY_ROOM_APRTMENT", "FOUR_ROOM_APARTMENT", "LAND",
				"ONE_ROOM_APARTMENT", "PARCEL", "THREE_ROOM_APARTMENT",
				"ONE_ROOM_APARTMENT"};
		Guest ivan = new Guest(1, "ivan@gmail.com", "0899123456", "Ivan");
		Guest stoyan = new Guest(2, "stoyan@gmail.com", "0899111111", "Stoyan");
		Guest petkan = new RegisteredUser(3, "petkan@gmail.com", "0899555555", "Petkan", "Petkanov", "pass1234", "Petko");
		Guest toshko = new Admin(4, "toshko@gmail.com", "0899777777", "Toshko", "Afrikanski", "admin", "Admin");
		Guest[] users = {ivan, stoyan, petkan, toshko};

		for (int i = 1; i < 101; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Property imot = null;
			try {
				imot = new Building(i,
						new Random().nextInt(cities.length) + 1, 
						"Centar", 
						new Random().nextInt(100000) + 50000, 
						new Random().nextInt(100) + 50,
						new Date(),
						new Random().nextInt(users.length) + 1,
						new Random().nextInt(typeArr.length) + 1,
						new Random().nextInt(10), 
						"Tuhla", 
						new Random().nextBoolean(), 
						new Random().nextBoolean(),
						"Super imot"
						);
			} catch (PropertyException e) {
				e.printStackTrace();
			}
			homes4You.addProperty(imot);
		}
		System.out.println(homes4You.sortPropertiesBy(SortCriteria.REGION));
		Integer[] arr = {1, 2, 3};
		Collection<Integer> coll = new ArrayList<Integer>(Arrays.asList(arr));
		System.out.println(homes4You.searchBy(1, coll, SortCriteria.SQUARE_METER_PRICE));
	}

}
