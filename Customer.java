import java.util.ArrayList;

public class Customer {
  private String customerID;
  private String name;
  
  String getCustomerID()
  {
    return this.customerID;
  }
  String getName()
  {
    return this.name; 
  }

  void setID(String ID)
  {
    this.customerID = ID; 
  }
  void setName(String name)
  {
    this.name = name; 
  }

  Vehicle requestRental(String name , ArrayList<Vehicle> vehicles)
  {
    for (Vehicle v : vehicles)
    {
      if(name.equalsIgnoreCase(v.getMakeModel()) )
      {
       if (v.isAvailable()) 
        {
          v.setStatus("rented");
          System.out.println("The vehicle " + v.getMakeModel() + " is rented SUCCESSFULLY!"); 
          return v;
        }
      }
    }
    System.out.println("The vehicle isn't available!");  
     return null;
  }
  float charge(Vehicle v , int days)
  {
    return (v.getDailyRate() * days);
  }
}
