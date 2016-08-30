package agency;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import property.Building;
import property.Property;
import property.Region;
import property.WrongPropertyException;
import user.Admin;
import user.Guest;
import user.RegisteredUser;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		
		Agency homes4You = new Agency();
		String[] cities = {"SOFIA", "PLOVDIV", "VARNA", "BURGAS", "RUSE"};
		String[] typeArr = {"HOUSE", "OFFICE", "GARAGE", 
				"MANY_ROOM_APRTMENT", "FOUR_ROOM_APARTMENT", "LAND",
				"ONE_ROOM_APARTMENT", "PARCEL", "THREE_ROOM_APARTMENT",
				"ONE_ROOM_APARTMENT"};
		Guest ivan = new Guest("ivan@gmail.com", "0899123456");
		Guest stoyan = new Guest("stoyan@gmail.com", "0899111111");
		Guest petkan = new RegisteredUser("petkan@gmail.com", "0899555555", "Petkan", "Petkanov", "pass1234");
		Guest toshko = new Admin("toshko@gmail.com", "0899777777", "Toshko", "Afrikanski", "admin");
		Guest[] users = {ivan, stoyan, petkan, toshko};

		for (int i = 0; i < 100; i++) {
			Thread.sleep(1);
			Property imot = null;
			try {
				imot = new Building(homes4You, cities[new Random().nextInt(cities.length)], 
						"Centar", new Random().nextInt(100000) + 50000, new Random().nextInt(100) + 50, 
						users[new Random().nextInt(users.length)], typeArr[new Random().nextInt(typeArr.length)], new Random().nextInt(10), 
						"Tuhla", new Random().nextBoolean(), new Random().nextBoolean());
			} catch (WrongPropertyException e) {
				e.printStackTrace();
			}
			homes4You.addProperty(imot);
		}
		System.out.println(homes4You.sortPropertiesBy(SortCriteria.REGION));
		String[] arr = {"HOUSE", "MANY_ROOM_APRTMENT"};
		Collection<String> coll = new ArrayList<String>(Arrays.asList(arr));
		System.out.println(homes4You.searchBy("Sofia", coll, SortCriteria.SQUARE_METER_PRICE));
	}
		
	
}
