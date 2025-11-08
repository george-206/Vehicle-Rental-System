public class Rental {
    private String rentalId;
    private String vehicleId;

    String getRentalId()
    {
        return this.rentalId;
    }
    String getVehicleID()
    {
        return vehicleId;
    }

    void setVehicleId(Vehicle v)
    {
       this.vehicleId = v.getVehicleId();
    }
    void setRentalID(String id)
    {
        this.rentalId = id;
    }
}
