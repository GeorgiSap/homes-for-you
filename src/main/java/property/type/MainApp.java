package property.type;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		PropertyTypeDAO propertyTypeJDBCTemplate = (PropertyTypeDAO) context.getBean("propertyTypeJDBCTemplate");

		propertyTypeJDBCTemplate.deleteAll();
		
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src" + File.separator + "main" + File.separator + "resources" +  File.separator + "property_types.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> propertyTypeList = new ArrayList<String>();
		while (sc.hasNextLine()) {
			propertyTypeList.add(sc.nextLine());
		}
		sc.close();

		System.out.println("------Records Creation--------");
		for (int propertyType = 0; propertyType < propertyTypeList.size(); propertyType++) {
			propertyTypeJDBCTemplate.create(new PropertyType(propertyType + 1, propertyTypeList.get(propertyType)));
		}

		System.out.println("------Listing Multiple Records--------");
		List<PropertyType> cities = propertyTypeJDBCTemplate.listPropertyTypes();
		for (PropertyType record : cities) {
			System.out.print("ID : " + record.getId());
			System.out.println(", Name : " + record.getName());
		}

		System.out.println("----Updating Record with ID = 1 -----");
		propertyTypeJDBCTemplate.update(1, "House");

		System.out.println("----Listing Record with ID = 1 -----");
		PropertyType propertyType = propertyTypeJDBCTemplate.getPropertyType(1);
		System.out.print("ID : " + propertyType.getId());
		System.out.println(", Name : " + propertyType.getName());

	}
}
