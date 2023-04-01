public class Builder {
    public static void main(String[] args) {
    AerospaceEngineer aero = new AerospaceEngineer();

    AirplaneBuilder crop = new CropDuster("Farmer Joe");
    AirplaneBuilder fighter = new CropDuster("The Navy");

    aero.setAirplaneBuilder(crop);
    aero.constructionAirplne();
    Airplane completeCropDuster = aero.getAirplane();
    System.out.println(completeCropDuster.getPowerplant());
    }
}

// Director
class AerospaceEngineer {
    private AirplaneBuilder airplaneBuilder;

    public void setAirplaneBuilder(AirplaneBuilder ab) {
        airplaneBuilder = ab;
    }

    public Airplane getAirplane() {
        return airplaneBuilder.getAirplane();
    }

    // step for making the airplane
    public void constructionAirplne() {
        airplaneBuilder.createNewAirplane();
        airplaneBuilder.buildWings();
        airplaneBuilder.buildPowerplant();
        airplaneBuilder.buildAvionics();
        airplaneBuilder.buildSeats();
    }
}


// AbstractBuilder
abstract class AirplaneBuilder {
    protected Airplane airplane;
    protected String customer;
    protected String type;

    public Airplane getAirplane() {
        return airplane;
    }

    public void createNewAirplane() {
        airplane = new Airplane(customer, type);
    }

    public abstract void buildWings();
    public abstract void buildPowerplant();
    public abstract void buildAvionics();
    public abstract void buildSeats();
}


// Product
class Airplane {
    private String type;
    private float wingspan;
    private String powerplant;
    private int crewSeats;
    private int passengerSeats;
    private String avionics;
    private String customer;

    Airplane(String customer, String type) {
        this.customer = customer;
        this.type = type;
    }

    public void setWinspan(float w) {this.wingspan = w;}
    public void setPowerPlant(String p) {this.powerplant = p;}
    public void setAvionics(String a) {this.avionics = a;}
    public void setNumberSeats(int crewSeats, int passengerSeats) {
        this.crewSeats = crewSeats;
        this.passengerSeats = passengerSeats;
    }
    public String getPowerplant() { return powerplant; }
    public String getCustomer() { return customer; }
    public String GetType() { return type; }
} 

// Concreate builder 1
class CropDuster extends AirplaneBuilder {
    CropDuster(String customer) {
        super.customer = customer;
        super.type = "Crop Duster v3.4";
    }

    public void buildWings() {
        airplane.setWinspan(9f);
    }

    public void buildPowerplant() {
        airplane.setPowerPlant("single piston");
    }

    public void buildAvionics() {}

    public void buildSeats() {
        airplane.setNumberSeats(1,1);
    }
}

// Concreate builder 2
class FightJet extends AirplaneBuilder {
    FightJet(String customer) {
        super.customer = customer;
        super.type = "F-35 Lightning II";
    }

    public void buildWings() {
        airplane.setWinspan(35f);
    }

    public void buildPowerplant() {
        airplane.setPowerPlant("dual thrust vectoring");
    }

    public void buildAvionics() {
        airplane.setAvionics("military");
    }

    public void buildSeats() {
        airplane.setNumberSeats(1,0);
    }
}