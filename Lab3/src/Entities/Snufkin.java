package Entities;

import Enums.*;
import Interfaces.ILocation;
import Locations.House;
import Locations.Kitchen;
import Locations.Lobby;
import Locations.Storeroom;

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

    public void putPot(Lobby lobby) {
        lobby.bringPot(1);
        System.out.println(this + " поставил горшок " + Prepositions.ON + " стол.");
    }

    public void grabFirewood(Storeroom storeroom) {
        storeroom.takeFirewood(20);
        System.out.println(this + " взял дрова.");
    }

    public void grabPot(Storeroom storeroom) {
        storeroom.takePot(1);
        System.out.println(this + " взял горшок.");
    }

    public void putFirewood(Kitchen kitchen) {
        kitchen.bringLogs(20);
        System.out.println(this + " положил дрова в печь.");

    }


    @Override
    public void moveToHuman(Human babies) {
        if (!babies.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println(this + " вернулся " + Prepositions.TO + " "
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
            System.out.println(this + " " + Traits.LOUD + " " + "забарабанил " + Prepositions.IN
                    + " дверь.");
            this.open(door);
        }
    }

    private void open(House.Door door) {
        if (door.isClosed()) {
            door.setStatus(ObjectStatus.OPENED);
            System.out.println(this + " " + Traits.ABRASIVELY + " открыл дверь.");
        }
    }

    public void smoke(Babies babies) {
        if (babies.LightUp()) {
            this.setHappiness(this.getHappiness() + 50);
            System.out.println(this + " счастлив: спичка загорелась, и " + this + " закурил.");
            this.setActiveStatus(ActiveStatus.SMOKING);
        } else {
            this.setHappiness(this.getHappiness() - 30);
            System.out.println(this + " расстроен: спичка не загорелась, так как ветер сильный дует. " +
                    "Так что, малыши не виноваты.");
            do {
                if (this.LightUp()) {
                    this.setHappiness(this.getHappiness() + 20);
                    System.out.println(this + " всё таки закурил.");
                    this.setActiveStatus(ActiveStatus.SMOKING);
                    break;
                }
            } while (!this.LightUp());
        }
    }

    public void burnFirewood(Kitchen kitchen, Storeroom.Firewood firewood) {
        if (kitchen.getLogsInStove() > 0) {
            do {
                if (this.LightUp() && firewood.getTraits().equals(TraitsOfFirewood.NON_BURNING)) {
                    firewood.setTraits(TraitsOfFirewood.BURNING);
                    System.out.println(firewood);
                    break;
                }
            } while (!this.LightUp());
        }
    }

    @Override
    public String toString() {
        return " Бродяга по имени " + this.getName();
    }

}
