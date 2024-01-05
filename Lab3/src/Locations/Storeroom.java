package Locations;

import Enums.TraitsOfLogs;
import Entities.Snufkin;

public class Storeroom extends Room {
    private final Logs logs = new Logs("Дрова", TraitsOfLogs.NON_BURNING);
    private int countOfPots = 1;

    @Override
    public void describe() {
        System.out.println("В кладовке " + logs + ".");
    }


    public void doActivities(Snufkin snufkin) {
        this.describe();
        snufkin.grabLogs(this);
        snufkin.grabPot(this);

    }

    public int getCountOfPots() {
        return countOfPots;
    }

    public void setCountOfPots(int countOfPots) {
        if (this.countOfPots >= 0){
            this.countOfPots = countOfPots;
        }
    }

    public void takeLogs(int amount) {
        this.logs.quantity -= amount;
    }

    public void takePot(int amount) {
        this.setCountOfPots(this.getCountOfPots() - amount);
    }

    public static class Logs {
        private final String name;
        int quantity = 100;
        private TraitsOfLogs traits;

        public Logs(String name, TraitsOfLogs traits) {
            this.name = name;
            this.traits = traits;
        }

        public String getName() {
            return name;
        }

        public void setTraits(TraitsOfLogs traits) {
            this.traits = traits;
        }

        @Override
        public String toString() {
            return "лежали " + this.getName();
        }

    }
}
