package jdbc;

import java.util.List;
import javax.sql.DataSource;

import user.Guest;

public interface GuestDAO {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Student table.
    */
   public void create(String email, String phoneNumber, String userName);
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public Guest getGuest(String email);
   /** 
    * This is the method to be used to list down
    * all the records from the Student table.
    */
   public List<Guest> listGuests();
   /** 
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public void delete(String email);
   /** 
    * This is the method to be used to update
    * a record into the Student table.
    */
   public void update(String email, String phoneNumber);
}
