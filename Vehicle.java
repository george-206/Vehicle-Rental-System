import java.time.LocalDate;

public class Vehicle {
    private String vehicleID;
    private String makeModel;
    private String type; // (car , bike)
    private String status; // (available , rented , maintenance)
    private float dailyRate;
    private LocalDate lastServiceDate;

   Vehicle(String vehicleID,String makeModel ,String type, String status, float dailyRate)
   {
      this.vehicleID = vehicleID;
      this.makeModel = makeModel;
      this.type = type;
      this.status = status;
      this.dailyRate = dailyRate;   
   }
    String getVehicleId()
    {
       return this.vehicleID;
    }
    String getMakeModel()
    {
       return this.makeModel;
    }
    String getType()
    {
       return this.type;
    }
    String getStatus()
    {
       return this.status;
    }
    float getDailyRate()
    {
       return this.dailyRate;
    }

    void setVehicleId(String id)
    {
       this.vehicleID = id;
    }
    void setMakeModel(String makeModel)
    {
       this.makeModel = makeModel;
    }
    void setType(String type)
    {
       this.type = type;
    }
    void setStatus(String status)
    {
       this.status = status;
    }
    void setDailyRate(float cost)
    {
       this.dailyRate = cost;
    }
    
    void display()
    {
       System.out.println("Vehicle ID: "+ vehicleID);
       System.out.println("Model: " + makeModel);
       System.out.println("Type: " + type );
       System.out.println("Status: " + status);
       System.out.println("Daily rate: " + dailyRate);
       System.out.println("Last service date: " + lastServiceDate);
       System.out.println("--------------------------------------");
    }
    boolean isAvailable()
    {
       return status.equalsIgnoreCase("available");
    }
    void markMaintenance(LocalDate date)
    {
       this.status = "maintenance";
       this.lastServiceDate = date;
    }

}
