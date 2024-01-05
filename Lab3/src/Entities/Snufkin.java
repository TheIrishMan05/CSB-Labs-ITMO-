package Entities;

import Enums.*;
import Interfaces.ILocation;
import Locations.*;
public class Snufkin extends Human {
    private int happiness = 50;

    public Snufkin(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        super(name, location, status, activeStatus);
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        if (this.happiness >= 0) {
            this.happiness = happiness;
        }
    }
    public void putPot(Lobby lobby){
        lobby.bringPot(1);
        System.out.println(this.toString() + " поставил горшок " + Prepositions.ON + " стол.");
    }
    public void grabLogs(Storeroom storeroom) {
        storeroom.takeLogs(20);
        System.out.println(this.toString() + " взял дрова.");
    }

    public void grabPot(Storeroom storeroom) {
        storeroom.takePot(1);
        System.out.println(this.toString() + " взял горшок.");
    }

    public void putLogs(Kitchen kitchen) {
        kitchen.bringLogs(20);
        System.out.println(this.toString() + " положил дрова в печь.");

    }


    @Override
    public void moveToHuman(Human babies) {
        if (!babies.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println(this.toString() + " вернулся " + Prepositions.TO + " "
                    + babies.toString().substring(0, babies.getName().length() - 1) + "ам.");
            this.setLocation(babies.getLocation());
            this.setStatus(Status.STANDING);
        }
    }

    @Override
    public void descend(Human babies) {
        if (babies.getStatus().equals(Status.ACTIVE)) {
            babies.setStatus(Status.SITTING);
            System.out.println(babies.toString().substring(0, babies.getName().length() - 1) + "ей поставили на пол.");
        }
    }

    public void knock(House.Door door) {
        if (door.isClosed()) {
            System.out.println(this.toString() + " " + Traits.LOUD + " " + "забарабанил " + Prepositions.IN
                    + " дверь.");
            this.open(door);
        }
    }

    private void open(House.Door door) {
        if (door.isClosed()) {
            door.setStatus(ObjectStatus.OPENED);
            System.out.println(this.toString() + " " + Traits.ABRASIVELY + " открыл дверь.");
        }
    }

    public void smoke(Babies babies) {
        if(!this.getActiveStatus().equals(ActiveStatus.SMOKING)) {
            if (babies.LightUp()) {
                this.setHappiness(this.getHappiness() + 50);
                System.out.println(this.toString() + " счастлив: спичка загорелась, и " + this.toString() + " закурил.");
                this.setActiveStatus(ActiveStatus.SMOKING);
            } else {
                this.setHappiness(this.getHappiness() - 30);
                System.out.println(this.toString() + " расстроен: спичка не загорелась - ветер сильный дует.");
                do {
                    if (this.LightUp()) {
                        this.setHappiness(this.getHappiness() + 20);
                        System.out.println(this.toString() + " всё таки закурил.");
                        this.setActiveStatus(ActiveStatus.SMOKING);
                        break;
                    }
                } while (!this.LightUp());
            }
        }
    }

    public void burnLogs(Kitchen kitchen, Storeroom.Logs logs) {
        if (kitchen.getLogsInStove() > 0) {
            do {
                if (this.LightUp()) {
                    logs.setTraits(TraitsOfLogs.BURNING);
                    System.out.println(logs.getName() + " горели в печи.");
                    break;
                }
            } while (!this.LightUp());
        }
    }
    @Override
    public String toString(){
        return " Бродяга по имени " + this.getName();
    }

}
