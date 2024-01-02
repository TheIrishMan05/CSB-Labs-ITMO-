package Humans;


import Common.Prepositions;
import Location.ILocation;
import Location.Room;

public class Babies extends Human {

    public Babies(String name, ILocation location, Status status) {
        super(name, location, status);
    }


    public void moveToHuman(Snufkin snufkin) {

    }

    @Override
    public boolean LightUp() {
        System.out.println(this.getName() + " попытались зажечь спичку.");
        return Math.random() >= 0.5;
    }


    @Override
    public String toString() {
        return this.getName();
    }


}
