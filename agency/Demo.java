package agency;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import property.Building;
import property.PropertyType;
import property.Region;
import property.WrongPropertyException;
import property.ContructionType;
import property.ListingType;
import property.Property;
import user.Admin;
import user.Guest;
import user.RegisteredUser;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		
		Agency homes4You = new Agency();
		Region[] cities = {Region.SOFIA, Region.PLOVDIV, Region.VARNA, Region.RUSE, Region.BURGAS};
		PropertyType[] typeArr = {PropertyType.HOUSE, PropertyType.OFFICE, PropertyType.GARAGE, 
				PropertyType.MANY_ROOM_APRTMENT, PropertyType.FOUR_ROOM_APARTMENT, PropertyType.LAND,
				PropertyType.ONE_ROOM_APARTMENT, PropertyType.PARCEL, PropertyType.THREE_ROOM_APARTMENT,
				PropertyType.ONE_ROOM_APARTMENT};
		Guest ivan = new Guest("ivan@gmail.com", "0899123456");
		Guest stoyan = new Guest("stoyan@gmail.com", "0899111111");
		Guest petkan = new RegisteredUser("petkan@gmail.com", "0899555555", "Petkan", "Petkanov", "pass1234");
		Guest toshko = new Admin("toshko@gmail.com", "0899777777", "Toshko", "Afrikanski", "admin");
		Guest[] users = {ivan, stoyan, petkan, toshko};
		RegisteredUser.setAgency(homes4You);
		for (int i = 0; i < 100; i++) {
			Thread.sleep(1);
			Property imot = null;
			try {
				imot = new Building(homes4You, ListingType.SALE, cities[new Random().nextInt(cities.length)], 
						"Centar", new Random().nextInt(100000) + 50000, new Random().nextInt(100) + 50, 
						users[new Random().nextInt(users.length)], typeArr[new Random().nextInt(typeArr.length)], new Random().nextInt(10), 
						ContructionType.TUHLA, new Random().nextBoolean(), new Random().nextBoolean());
			} catch (WrongPropertyException e) {
				e.printStackTrace();
			}
			homes4You.addProperty(imot);
		}
		System.out.println(homes4You.sortPropertiesBy(SortCriteria.REGION));
		PropertyType[] arr = {PropertyType.HOUSE, PropertyType.MANY_ROOM_APRTMENT};
		Collection<PropertyType> coll = new ArrayList<PropertyType>(Arrays.asList(arr));
		//System.out.println(homes4You.searchBy("Sofia", coll, SortCriteria.SQUARE_METER_PRICE));
	}
		
	
}
