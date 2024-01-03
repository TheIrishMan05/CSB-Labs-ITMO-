package Humans;

import Common.House;
import Common.ObjectStatus;
import Common.Prepositions;
import Location.*;


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

    @Override
    public void grabLogs(Storeroom storeroom) {
        storeroom.takeLogs(20);
        System.out.println(this.getName() + " взял дрова.");
    }

    @Override
    public void grabPot(Storeroom storeroom) {
        storeroom.takePot(1);
        System.out.println(this.getName() + " взял горшок.");
    }

    @Override
    public void putLogs(Kitchen kitchen) {
        kitchen.bringLogs(20);
        System.out.println(this.getName() + " положил дрова в печь.");

    }

    public void enterLocation(ILocation location) {
        if (this.getStatus().equals(Status.ACTIVE) && (this.getLocation() instanceof Lobby
                || this.getLocation() instanceof Kitchen || this.getLocation() instanceof Storeroom)) {
            this.setLocation(location);
        }
    }

    @Override
    public void moveToHuman(Human babies) {
        if (babies instanceof Babies && !babies.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println(this.getName() + " вернулся " + Prepositions.TO + " "
                    + babies.getName().substring(0, babies.getName().length() - 1) + "ам.");
            this.setLocation(babies.getLocation());
            this.setStatus(Status.STATIC);
        }
    }

    @Override
    public void descend(Human babies) {
        if (babies.getLocation() instanceof Lobby && babies instanceof Babies) {
            babies.setStatus(Status.STATIC);
            System.out.println(babies.getName().substring(0, babies.getName().length() - 1) + "ей поставили на пол.");
        }
    }

    public void knock(House.Door door) {
        if (door.knockable()) {
            System.out.println(this.getName() + " " + Traits.LOUD + " " + "забарабанил " + Prepositions.IN
                    + " дверь.");
            this.open(door);
        }
    }

    private void open(House.Door door) {
        if (door.getStatus().equals(ObjectStatus.CLOSED)) {
            door.setStatus(ObjectStatus.OPENED);
            System.out.println(this.getName() + " " + Traits.ABRASIVELY + " открыл дверь.");
        }
    }

    public void smoke(Babies babies) {
        if (babies.LightUp()) {
            this.setHappiness(this.getHappiness() + 50);
            System.out.println(this.getName() + " счастлив: спичка загорелась, и " + this.getName() + " закурил.");
            this.setActiveStatus(ActiveStatus.SMOKING);
        } else {
            this.setHappiness(this.getHappiness() - 30);
            System.out.println(this.getName() + " расстроен: спичка не загорелась - ветер сильный дует.");
            do {
                if (this.LightUp()) {
                    this.setHappiness(this.getHappiness() + 20);
                    System.out.println(this.getName() + " всё таки закурил.");
                    this.setActiveStatus(ActiveStatus.SMOKING);
                    break;
                }
            } while (!this.LightUp());
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

}
