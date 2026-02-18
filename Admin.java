import java.util.ArrayList;

public class Admin {
    private String userName;
    private String password;

    Admin(String userName , String password)
    {
        this.userName = userName;
        this.password = password;
    }
    void setUserName(String userName)
    {
       this.userName = userName;
    }
    void setPassword(String password)
    {
       this.password = password;
    }

    void addVehicle(Vehicle v , ArrayList<Vehicle> vehicles)
    {
        vehicles.add(v);
        System.out.println("Vehicle " + v.getMakeModel() + " added SUCCESSFULLY!");
    }
    void viewAvailable(ArrayList<Vehicle> vehicles)
    {
       for (Vehicle vehicle : vehicles) {
          if (vehicle.isAvailable()) {
            vehicle.display();
          }
       }
    }
    void viewInMaintenance(ArrayList<Vehicle> vehicles)
    {
        int count = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getStatus().equalsIgnoreCase("maintenance") ) {
                System.out.println(vehicle.getMakeModel() + " is in MAINTENANCE");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("There are no vehicles in maintenance");
        }
    }
    void changeStatus(String modelName , String st , ArrayList<Vehicle> vehicles)
    {
       boolean found = false;
      
         for (Vehicle vehicle_Item : vehicles) 
            {
              if (modelName.equalsIgnoreCase(vehicle_Item.getMakeModel())) 
                  {
                     if(st.equalsIgnoreCase("available"))
                     {
                       vehicle_Item.setStatus("available");
                       System.out.println("Changed to \"available\" successfully");
                     }
                     else if(st.equalsIgnoreCase("maintenance"))
                     {
                      vehicle_Item.setStatus("maintenance");
                      System.out.println("Changed to \"maintenance\" successfully");
                     }
                     found = true;
                    break;
                  }  
             }
           if (!found) 
           {
            System.out.println("The vehicle is not found");  
           }
    }
}
