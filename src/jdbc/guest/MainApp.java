package jdbc.guest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jdbc.guest.GuestJDBCTemplate;
import user.Guest;

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = 
              new ClassPathXmlApplicationContext("Beans.xml");
      GuestJDBCTemplate guestJDBCTemplate = 
      (GuestJDBCTemplate)context.getBean("guestJDBCTemplate");
      
      System.out.println("------Records Deletion--------" );
      guestJDBCTemplate.deleteAll();
      
      
      System.out.println("------Records Creation--------" );
      guestJDBCTemplate.create("gosho@gmail.com", "0899999999", "Gosho");
      guestJDBCTemplate.create("pesho@gmail.com", "0899555555", "Pesho");
      guestJDBCTemplate.create("ivan@gmail.com", "0899777777", "Ivan");

      System.out.println("------Listing Multiple Records--------" );
      List<Guest> guests = guestJDBCTemplate.listGuests();
      for (Guest record : guests) {
         System.out.print("EMail : " + record.getEmail() );
         System.out.print(", Phone : " + record.getPhoneNumber() );
         System.out.println(", User Name : " + record.getUserName());
      }

      System.out.println("----Updating Record with Email = pesho@gmail.com -----" );
      guestJDBCTemplate.update("pesho@gmail.com", "0899444444");

      System.out.println("----Listing Record with Email = pesho@gmail.com -----" );
      Guest guest = guestJDBCTemplate.getGuest("pesho@gmail.com");
      System.out.print("EMail : " + guest.getEmail() );
      System.out.print(", Phone : " + guest.getPhoneNumber() );
      System.out.println(", User Name : " + guest.getUserName());
	  
   }
}
