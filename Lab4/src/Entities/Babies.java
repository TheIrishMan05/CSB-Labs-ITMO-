package Entities;


import Enums.ActiveStatus;
import Enums.Prepositions;
import Enums.Status;
import Interfaces.ILocation;
import Locations.Storeroom;

public class Babies extends Human {
    private int nourishment = 40;
    private int happiness = 60;

    public Babies(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        super(name, location, status, activeStatus);
    }

    public int getNourishment() {
        return nourishment;
    }

    public void setNourishment(int nourishment) {
        if (this.nourishment >= 0) {
            this.nourishment = nourishment;
        }
    }




    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        if (this.happiness >= 0) {
            this.happiness = happiness;
        }
    }

    @Override
    public void moveToHuman(Human snufkin) {
        if (snufkin.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println("Тем временем " + this + " доползли " + Prepositions.TO + " " + snufkin.getName() + "у.");
            this.setStatus(Status.STANDING);
        }
    }


    @Override
    public String toString() {
        return this.getName();
    }

    public void eat(Storeroom.Pot pot) {
        if (pot.getKgOfBeans() > 0) {
            while (this.getNourishment() <= 100) {
                pot.setKgOfBeans(pot.getKgOfBeans() - 0.05);
                this.setNourishment(this.getNourishment() + 10);
                this.setHappiness(this.getHappiness() + 10);
                if (this.getNourishment() == 100 && this.getHappiness() >= 75) {
                    System.out.println("Малыши были сыты и довольны.");
                    break;
                }
            }
        }
    }

}
