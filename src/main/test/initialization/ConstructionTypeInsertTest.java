package initialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import property.building.type.ConstructionType;
import property.building.type.ConstructionTypeDAO;

public class ConstructionTypeInsertTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ConstructionTypeDAO constructionTypeJDBCTemplate = (ConstructionTypeDAO) context.getBean("constructionTypeJDBCTemplate");

		constructionTypeJDBCTemplate.deleteAll();
		
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src" + File.separator + "main" + File.separator + "resources" +  File.separator + "construction_types.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> constructionTypeList = new ArrayList<String>();
		while (sc.hasNextLine()) {
			constructionTypeList.add(sc.nextLine());
		}
		sc.close();

		System.out.println("------Records Creation--------");
		for (int constructionType = 0; constructionType < constructionTypeList.size(); constructionType++) {
			constructionTypeJDBCTemplate.create(new ConstructionType(constructionType + 1, constructionTypeList.get(constructionType)));
		}

		System.out.println("------Listing Multiple Records--------");
		List<ConstructionType> cities = constructionTypeJDBCTemplate.listConstructionTypes();
		for (ConstructionType record : cities) {
			System.out.print("ID : " + record.getId());
			System.out.println(", Name : " + record.getName());
		}

		System.out.println("----Updating Record with ID = 1 -----");
		constructionTypeJDBCTemplate.update(1, "TUHLA");

		System.out.println("----Listing Record with ID = 1 -----");
		ConstructionType constructionType = constructionTypeJDBCTemplate.getConstructionType(1);
		System.out.print("ID : " + constructionType.getId());
		System.out.println(", Name : " + constructionType.getName());
	}

}
