package Locations;

import Entities.Babies;
import Entities.Snufkin;
import Enums.TraitsOfClock;
import Enums.TraitsOfFlowers;

public class Lobby extends Room {

    private final Clock clock = new Clock(TraitsOfClock.STOPPED);
    private final Table table = new Table();
    private final Table.Flowers flowers = table.new Flowers(TraitsOfFlowers.FADED);
    private final Storeroom.Pot pot = new Storeroom.Pot();

    @Override
    public void describe() {
        System.out.println("В прихожей " + table + ", на котором " + flowers + ". На стене "
                + clock);
    }


    public void doActivities(Snufkin snufkin, Babies babies, Storeroom.Pot pot, Rope rope) {
        this.describe();
        snufkin.descend(babies);
        snufkin.putPot(this);
        babies.moveToHuman(snufkin);
        snufkin.hang(rope);
        snufkin.smoke(babies);
        babies.eat(pot);
    }

    public void bringPot(int amount) {
        this.pot.setCountOfPots(this.pot.getCountOfPots() + amount);
        this.pot.setKgOfBeans(2 * this.pot.getCountOfPots());
    }

    class Clock {
        private TraitsOfClock status;

        Clock(TraitsOfClock status) {
            this.status = status;
        }

        @Override
        public String toString() {
            if (Math.random() >= 0.5) {
                clock.setStatus(TraitsOfClock.WORKING);
            } else {
                clock.setStatus(TraitsOfClock.STOPPED);
            }
            return " висели " + this.getStatus() + " часы.";
        }

        public TraitsOfClock getStatus() {
            return status;
        }

        public void setStatus(TraitsOfClock status) {
            this.status = status;
        }
    }

    class Table {
        @Override
        public String toString() {
            return " стоял стол ";
        }

        class Flowers {
            private TraitsOfFlowers status;

            Flowers(TraitsOfFlowers status) {
                this.status = status;
            }

            public TraitsOfFlowers getStatus() {
                return status;
            }

            public void setStatus(TraitsOfFlowers status) {
                this.status = status;
            }

            @Override
            public String toString() {
                if (Math.random() >= 0.5) {
                    this.setStatus(TraitsOfFlowers.NOTFADED);
                } else {
                    this.setStatus(TraitsOfFlowers.FADED);
                }
                return " стояли " + this.getStatus() + " цветы ";
            }
        }
    }
}
