import java.util.Scanner;
import java.util.ArrayList;


public class Program {
    public static void main(String[] args)
    {
      Scanner sc = new Scanner(System.in);
      System.out.println("Welcome to Vehicle Rental System");
      System.out.println("--------------------------------");
      
      ArrayList<Vehicle> vehicles = new ArrayList<>();
      vehicles.add(new Vehicle("V1001", "Toyota Corolla", "Car", "available", 35.0f));
      vehicles.add(new Vehicle("V1002", "Honda Civic", "Car","available", 40.0f));

      char c = ' ';  
      System.out.println("Choose: \n[1] to login as an admin\n[2] to log in as a user" +
      "\n[3] to Quit");
      do 
      {
       c = sc.next().charAt(0);
      } while (c != '1' && c != '2' && c != '3');
      sc.nextLine();

      String userName = "";
      String password = "";
      Admin a1 = new Admin(userName, password);

      Customer c1 = new Customer();
      String customerName = "";
      
      int rentalCount = 1;
      while (true) 
      {       
        
        switch (c)
        {
          case '1': // Admin
             try  
             {
               System.out.print("Write the user name: "); //admin input
               userName = sc.nextLine();
               System.out.print("Write your password: ");
               password = sc.nextLine();
               if(userName.isEmpty() || password.isEmpty())
               {
                  throw new Exception("The username and password should not be empty");
               }
             } 
              catch (Exception e) 
             {
               System.out.println(e.getMessage());
               continue;
             }
           
              
              a1.setUserName(userName);
              a1.setPassword(password);
              System.out.println("Choose: \n[1] to add a vehicle" + 
              "\n[2] to view available vehicles" +
              "\n[3] to view the vehicles that are in maintenance");
              char adminChoice;
              do {
                 adminChoice = sc.next().charAt(0);
              } while (adminChoice != '1' && adminChoice != '2' && adminChoice != '3');
              sc.nextLine();
              switch (adminChoice) {
                case '1':
                  System.out.print("Write the ID: ");
                  boolean flag = true;
                  String id = "";
                  while (flag) 
                  {
                     id = sc.nextLine(); 
                     int count = 0;
                    for (Vehicle vehicle : vehicles) 
                    {
                      if(id.equals(vehicle.getVehicleId()))
                      {
                        System.out.println("This id is taken, write another one");
                        break;
                      }
                      count++;
                    }
                    if(count == vehicles.size())
                       flag = false;
                  }
                  
                  System.out.print("Write the Model: ");
                  String model = sc.nextLine();

                  System.out.print("Write the type(car,bike): ");
                  String type = sc.nextLine();

                  System.out.print("Write the daily cost: ");
                  float dailyRate = 0;
                  do {
                    dailyRate = sc.nextFloat();
                  } while (dailyRate <=0);

                  Vehicle v = new Vehicle(id, model, type,"available", dailyRate);
                  a1.addVehicle(v, vehicles);
                break;

                case '2':
                  a1.viewAvailable(vehicles);
                break;  

                case '3':
                 a1.viewInMaintenance(vehicles);
                break;
                
                default:
                  break;
              }
              break;
          case '2': // user
            
              // user Input
            
              System.out.print("Write your name: ");
               do 
              {
                customerName = sc.nextLine();
              } while (customerName.isEmpty());
            
            
            c1.setName(customerName);
            
            displayVehicles(vehicles);
            System.out.print("Write the Model of the vehicle that you want to rent: ");
            String carModel = sc.nextLine();
            Vehicle rentedVehicle = c1.requestRental(carModel, vehicles); 
            if (rentedVehicle != null) 
            {
               System.out.print("How many days do you want to rent the vehicle? ");
               int days = 0;
               do 
               {
                  days = sc.nextInt();
               } while (days <=0);
              
               Rental rental = new Rental();
               System.out.println("--------------------");
               System.out.println("Here is your receipt");
               System.out.println("      --------");
               rental.setRentalID("R100" + rentalCount);
               rental.setVehicleId(rentedVehicle);
               rentalCount++;
               float totalCharge = c1.charge(rentedVehicle, days);
               System.out.println("Receipt ID: " + rental.getRentalId());
               System.out.println("Vehicle ID: " + rental.getVehicleID());
               System.out.println("Total charge: $"+ totalCharge);
               System.out.println("--------------------");  
            }  
            
          break;
            
        }
        System.out.println("To continue press [1] for admin or [2] for user\nTo quit press [3]");
        c = sc.next().charAt(0);
        sc.nextLine();
        if(c == '3')
           break;
      }
       
      sc.close(); 
    }

    //Methods
    static void displayVehicles(ArrayList<Vehicle> vehicles)
    {
       System.out.println("The vehicles: ");
        for (Vehicle vehicle : vehicles) 
        {
              System.out.println("Type: " + vehicle.getType());
              System.out.println("Model: " + vehicle.getMakeModel());
              System.out.println("Daily cost: " + vehicle.getDailyRate());  
              System.out.println("Status: " + vehicle.getStatus());
              System.out.println("------------------------------------");
        }
    }
}
