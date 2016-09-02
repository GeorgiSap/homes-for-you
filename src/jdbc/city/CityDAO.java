package jdbc.city;

import java.util.List;

import javax.sql.DataSource;


public interface CityDAO {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Student table.
    */
   public void create(int id, String name);
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public City getCity(int id);
   /** 
    * This is the method to be used to list down
    * all the records from the Student table.
    */
   public List<City> listCities();
   /** 
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public void delete(int id);
   /** 
    * This is the method to be used to update
    * a record into the Student table.
    */
   public void update(int id, String name);
}
