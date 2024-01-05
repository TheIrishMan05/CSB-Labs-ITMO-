package Entities;


import Enums.ActiveStatus;
import Enums.Prepositions;
import Enums.Status;
import Interfaces.ILocation;
public class Babies extends Human {

    public Babies(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        super(name, location, status, activeStatus);
    }

    @Override
    public void moveToHuman(Human snufkin) {
        if (snufkin.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println(" Тем временем " + this.toString() + " доползли " + Prepositions.TO + " " + snufkin.getName() + "у.");
            this.setStatus(Status.STANDING);
        }
    }

    @Override
    public boolean LightUp() {
        System.out.println(this.toString() + " попытались зажечь спичку.");
        return Math.random() >= 0.5;
    }


    @Override
    public String toString() {
        return this.getName();
    }


}
