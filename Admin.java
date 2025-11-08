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
}
