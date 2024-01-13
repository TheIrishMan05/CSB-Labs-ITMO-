package Entities;

import Enums.*;
import Exceptions.CarbonMonoxidePoisoningException;
import Exceptions.NoFirewoodException;
import Exceptions.RopeOverloadException;
import Interfaces.ILocation;
import Locations.*;

import java.util.Timer;
import java.util.TimerTask;

public class Snufkin extends Human {
    private int happiness = 50;
    private int amountOfClothes = 5;

    public Snufkin(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        super(name, location, status, activeStatus);
    }

    public int getAmountOfClothes() {
        return amountOfClothes;
    }

    public void setAmountOfClothes(int amountOfClothes) {
        if (this.amountOfClothes >= 0) {
            this.amountOfClothes = amountOfClothes;
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


    public void hang(Rope rope) {
        int hungClothesCounter = 0;
        while (this.getAmountOfClothes() > 0) {
            if (hungClothesCounter < rope.getClothes().length) {
                if (rope.getClothes()[hungClothesCounter] == null) {
                    if (hungClothesCounter % 2 != 0) {
                        rope.getClothes()[hungClothesCounter] = new Rope.Clothes("штанишки");
                        System.out.println(this + " повесил " + rope.getClothes()[hungClothesCounter]);
                    } else {
                        rope.getClothes()[hungClothesCounter] = new Rope.Clothes("платьице");
                        System.out.println(this + " повесил " + rope.getClothes()[hungClothesCounter]);
                    }
                }
                hungClothesCounter += 1;
                this.setAmountOfClothes(this.getAmountOfClothes() - 1);
            } else {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        while (rope.getDurability() > 0) {
                            rope.setDurability(rope.getDurability() - 5);
                            if (rope.getDurability() == 0) {
                                rope.setStatus(ObjectStatus.RIPPED);
                                throw new RopeOverloadException("На верёвку повесили слишком много вещей" +
                                        " и она порвалась, " +
                                        "через некоторое время.");
                            }
                        }
                    }
                };
                timer.schedule(timerTask, 1000);
            }
        }
    }

    public void putPot(Lobby lobby) {
        lobby.bringPot(1);
        System.out.println(this + " поставил горшок " + Prepositions.ON + " стол.");
    }

    public void grabFirewood(Storeroom storeroom) {
        try {
            storeroom.takeFirewood(20);
            System.out.println(this + " взял дрова.");
        } catch (NoFirewoodException nfe) {
            System.out.println("\u001B[33m" + "Дров не хватит, надо взять поменьше." + "\u001B[0m");
        }
    }

    public void grabPot(Storeroom storeroom) {
        storeroom.takePot(1);
        System.out.println(this + " взял горшок.");
    }

    public void putFirewood(Kitchen kitchen) {
        kitchen.bringFirewood(20);
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
            door.setStatus(CloseAbleStatus.OPENED);
            System.out.println(this + " " + Traits.ABRASIVELY + " открыл дверь.");
        }
    }

    public void smoke(Human babies) {
        if (babies.LightUp() == true) {
            this.setHappiness(this.getHappiness() + 50);
            System.out.println(this + " счастлив: спичка загорелась, и " + this + " закурил.");
            this.setActiveStatus(ActiveStatus.SMOKING);
        } else {
            this.setHappiness(this.getHappiness() - 30);
            System.out.println(this + " расстроен: спичка не загорелась, так как ветер сильный дует. " +
                    "Так что, малыши не виноваты.");
            while (this.LightUp() == false) {
                if (this.LightUp() == true) {
                    this.setHappiness(this.getHappiness() + 20);
                    System.out.println(this + " всё таки закурил.");
                    this.setActiveStatus(ActiveStatus.SMOKING);
                    break;
                }
            }
        }
    }

    public void burnFirewood(Kitchen.Stove stove, Storeroom.Firewood firewood, Human babies) {
        if (stove.getFirewoodInStove() > 0) {
            while (this.LightUp() == false) {
                if (this.LightUp() == true && firewood.getTraits().equals(TraitsOfFirewood.NON_BURNING)) {
                    firewood.setTraits(TraitsOfFirewood.BURNING);
                    System.out.println(firewood);
                    if (stove.isClosed()) {
                        if (Math.random() <= 0.9) {
                            stove.setShuffleStatus(CloseAbleStatus.OPENED);
                            System.out.println(this + " успел открыть заслонку печки. Все остались живы.");
                            break;
                        } else {
                            Timer timer = new Timer();
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    throw new CarbonMonoxidePoisoningException("К сожалению, " +
                                            "Снусмумрик" + " не смог открыть заслонку печки вовремя, " +
                                            "и все люди скончались от отравления" +
                                            " угарным газом через некоторое время после начала горения дров.");
                                }
                            };
                            timer.schedule(timerTask, 5000);
                            this.setStatus(Status.DEAD);
                            babies.setStatus(Status.DEAD);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Бродяга по имени " + this.getName();
    }

}
