package Locations;

import Entities.Snufkin;
import Enums.TraitsOfFirewood;

public class Storeroom extends Room {
    private final Firewood firewood = new Firewood(TraitsOfFirewood.NON_BURNING);
    private int countOfPots = 1;

    @Override
    public void describe() {
        System.out.println("В кладовке " + firewood);
    }


    public void doActivities(Snufkin snufkin) {
        this.describe();
        snufkin.grabFirewood(this);
        snufkin.grabPot(this);

    }

    public int getCountOfPots() {
        return countOfPots;
    }

    public void setCountOfPots(int countOfPots) {
        if (this.countOfPots > 0) {
            this.countOfPots = countOfPots;
        }
    }

    public void takeFirewood(int amount) {
        if (this.firewood.getQuantity() >= amount) {
            this.firewood.setQuantity(this.firewood.getQuantity() - amount);
        }
    }

    public void takePot(int amount) {
        if (this.getCountOfPots() >= amount) {
            this.setCountOfPots(this.getCountOfPots() - amount);
        }
    }

    public static class Firewood {
        int quantity = 100;
        private TraitsOfFirewood traits;

        public Firewood(TraitsOfFirewood traits) {
            this.traits = traits;
        }

        public TraitsOfFirewood getTraits() {
            return traits;
        }

        public void setTraits(TraitsOfFirewood traits) {
            this.traits = traits;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            if (this.quantity > 0) {
                this.quantity = quantity;
            }
        }

        @Override
        public String toString() {
            if (this.getTraits().equals(TraitsOfFirewood.NON_BURNING)) {
                return "лежали дрова.";
            } else {
                return "Дрова горели в печи.";
            }
        }

    }
}
