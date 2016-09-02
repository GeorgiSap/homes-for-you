package jdbc.city;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CityJDBCTemplate cityJDBCTemplate = (CityJDBCTemplate) context.getBean("cityJDBCTemplate");

		System.out.println("------Records Deletion--------");
		cityJDBCTemplate.deleteAll();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("mySQL" + File.separator + "cities.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> cityList = new ArrayList<String>();
		while (sc.hasNextLine()) {
			cityList.add(sc.nextLine());
		}
		sc.close();

		System.out.println("------Records Creation--------");
		for (int city = 0; city < cityList.size(); city++) {
			cityJDBCTemplate.create(city + 1, cityList.get(city));
		}

		System.out.println("------Listing Multiple Records--------");
		List<City> cities = cityJDBCTemplate.listCities();
		for (City record : cities) {
			System.out.print("ID : " + record.getId());
			System.out.println(", Name : " + record.getName());
		}

		System.out.println("----Updating Record with ID = 1 -----");
		cityJDBCTemplate.update(1, "Sofia-grad");

		System.out.println("----Listing Record with ID = 1 -----");
		City city = cityJDBCTemplate.getCity(1);
		System.out.print("ID : " + city.getId());
		System.out.println(", Name : " + city.getName());

	}
}
