package initialization;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import property.land.type.LandType;
import property.land.type.LandTypeDAO;

public class LandTypeInsertTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		LandTypeDAO landTypeJDBCTemplate = (LandTypeDAO) context.getBean("landTypeJDBCTemplate");

		landTypeJDBCTemplate.deleteAll();
		
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src" + File.separator + "main" + File.separator + "resources" +  File.separator + "land_types.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> landTypeList = new ArrayList<String>();
		while (sc.hasNextLine()) {
			landTypeList.add(sc.nextLine());
		}
		sc.close();

		System.out.println("------Records Creation--------");
		for (int landType = 0; landType < landTypeList.size(); landType++) {
			landTypeJDBCTemplate.create(new LandType(landType + 1, landTypeList.get(landType)));
		}

		System.out.println("------Listing Multiple Records--------");
		List<LandType> cities = landTypeJDBCTemplate.listLandTypes();
		for (LandType record : cities) {
			System.out.print("ID : " + record.getId());
			System.out.println(", Name : " + record.getName());
		}

		System.out.println("----Updating Record with ID = 1 -----");
		landTypeJDBCTemplate.update(1, "GRADINA");

		System.out.println("----Listing Record with ID = 1 -----");
		LandType landType = landTypeJDBCTemplate.getLandType(1);
		System.out.print("ID : " + landType.getId());
		System.out.println(", Name : " + landType.getName());
	}

}
