import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Program {
    public static void main(String[] args)
    {
      Scanner sc = new Scanner(System.in);
      System.out.println("Welcome to Vehicle Rental System");
      System.out.println("--------------------------------");
      
      ArrayList<Vehicle> vehicles = new ArrayList<>();
      try 
      {
        File f = new File("Vehicles.txt"); // Reading From File
        Scanner reader = new Scanner(f);
        while (reader.hasNextLine()) 
        {
           String line = reader.nextLine();
           StringBuilder sb = new StringBuilder();
           String[] strings = new String[5];
           int index = 0;
          for (int i = 0; i < line.length(); i++) 
          {
            if(line.charAt(i) != ',')
            {
              sb.append(line.charAt(i));
            }
            if(line.charAt(i) == ',' || i == line.length()-1)
            {
               strings[index] = sb.toString();
               index++;
               sb.setLength(0);
            }
          }
          try 
          {
              float dailyRate = Float.parseFloat(strings[4]);
              vehicles.add(new Vehicle(strings[0], strings[1], strings[2], strings[3], dailyRate));
          } 
          catch (NumberFormatException e)
           {
              System.out.println("Error: Daily Rate is not a valid number in line: " + line);
           }
        }
        reader.close();
      }
      catch (Exception e) 
      {
        System.out.println("Error In Extracting data: " + e.getMessage());
      }
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
              "\n[3] to view the vehicles that are in maintenance" +
              "\n[4] to set the status of the vehicle");
              char adminChoice;
              do 
              {
                 adminChoice = sc.next().charAt(0);
              } while (adminChoice != '1' && adminChoice != '2' && adminChoice != '3' && adminChoice != '4');
              sc.nextLine();
              
              switch (adminChoice) 
              {
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
                  do 
                  {
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

                case '4':
                    System.out.print("Write the name of the vehicle that you want to change its status: ");
                    String modelName = sc.nextLine();
                    System.out.print("Write the desired status: ");
                    String st;
                    do 
                    {
                      st = sc.nextLine();
                    } while (!st.equalsIgnoreCase("available") && !st.equalsIgnoreCase("maintenance"));
                   a1.changeStatus(modelName, st, vehicles);  
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
               System.out.print("How many days do you want to rent the vehicle? "); // date & time
               int totalDays = 0;
               do 
               {
                  totalDays = sc.nextInt();
               } while (totalDays <=0);
               sc.nextLine();
               System.out.println("Write the date in this format: year-month-day (eg:2000-01-01)");
               String date;
               do
               {
                 date = sc.nextLine();
               } while (date.length() < 10 || date.charAt(4) != '-' || date.charAt(7) != '-');
               
               DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               LocalDate startDate = LocalDate.parse(date , format);
               rentedVehicle.setLastServiceDate(startDate , totalDays);

               Rental rental = new Rental();                          //   receipt
               System.out.println("--------------------");
               System.out.println("Here is your receipt");
               System.out.println("      --------");
               rental.setRentalID("R100" + rentalCount);
               rental.setVehicleId(rentedVehicle);
               rentalCount++;
               float totalCharge = c1.charge(rentedVehicle, totalDays);
               System.out.println("Receipt ID: " + rental.getRentalId());
               System.out.println("Vehicle ID: " + rental.getVehicleID());
               System.out.println("Customer Name: " + c1.getName());
               System.out.println("Start date: " + startDate + "\nEnd date: " + rentedVehicle.getLastServiceDate());
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
      
      try 
      { 
        FileWriter fWriter = new FileWriter("Vehicles.txt"); // Writing in a file
        for (Vehicle vehicle : vehicles) 
        {
          fWriter.write(vehicle.getVehicleId() + "," + vehicle.getMakeModel() + "," +
          vehicle.getType() + "," + vehicle.getStatus() + "," + vehicle.getDailyRate() + "\n");  
        }
        fWriter.close();
      } 
      catch (Exception e)
      {
        System.out.println("Error Writing File: " + e.getMessage());
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
