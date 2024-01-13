package Locations;

import Entities.Snufkin;
import Enums.TraitsOfFirewood;
import Exceptions.NoFirewoodException;

import java.util.Objects;

public class Storeroom extends Room {
    private final Firewood firewood = new Firewood(TraitsOfFirewood.NON_BURNING);
    private final Pot pot = new Pot();

    @Override
    public void describe() {
        System.out.println("В кладовке " + firewood);
    }


    public void doActivities(Snufkin snufkin) {
        this.describe();
        snufkin.grabFirewood(this);
        snufkin.grabPot(this);

    }

    public void takeFirewood(int amount) throws NoFirewoodException {
        if (this.firewood.getQuantity() < amount)
            throw new NoFirewoodException("Дрова закончились");
        else {
            this.firewood.setQuantity(this.firewood.getQuantity() - amount);
        }
    }

    public void takePot(int amount) {
        if (this.pot.getCountOfPots() >= amount) {
            this.pot.setCountOfPots(this.pot.getCountOfPots() - amount);
            this.pot.setKgOfBeans(this.pot.getKgOfBeans() - 2 * amount);
        }
    }

    public static class Firewood {
        private int quantity = 100;
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
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Firewood firewood = (Firewood) o;
            return Objects.equals(traits, firewood.traits);
        }

        @Override
        public int hashCode() {
            return Objects.hash(traits);
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

    public static class Pot {
        private int countOfPots = 1;
        private double kgOfBeans = 2 * this.getCountOfPots();

        public int getCountOfPots() {
            return countOfPots;
        }

        public void setCountOfPots(int countOfPots) {
            if (this.countOfPots > 0) {
                this.countOfPots = countOfPots;
            }
        }

        public double getKgOfBeans() {
            return kgOfBeans;
        }

        public void setKgOfBeans(double kgOfBeans) {
            if (this.kgOfBeans >= 0) {
                this.kgOfBeans = kgOfBeans;
            }
        }
    }
}
