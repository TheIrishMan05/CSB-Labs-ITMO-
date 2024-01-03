package Humans;


import Common.Prepositions;
import Location.ILocation;

public class Babies extends Human {

    public Babies(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        super(name, location, status, activeStatus);
    }

    @Override
    public void moveToHuman(Human snufkin) {
        if (snufkin.getLocation().equals(this.getLocation()) && snufkin instanceof Snufkin) {
            this.setStatus(Status.ACTIVE);
            System.out.println(" Тем временем " + this.getName() + " доползли " + Prepositions.TO + " " + snufkin.getName() + "у.");
            this.setStatus(Status.STATIC);
        }
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
