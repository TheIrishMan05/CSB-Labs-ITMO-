package Location;

import Humans.Babies;
import Humans.Human;
import Humans.Snufkin;

public class Lobby extends Room {

    private Clock clock;
    private Table table;
    private Table.Flowers flowers;
    private int amountOfPots = 0;
    @Override
    public void describe() {
        System.out.println("В прихожей " + table.toString() + ", на котором " + flowers.toString() + ". На стене "
                + clock.toString());
    }


    public void doActivities(Snufkin snufkin, Babies babies) {
        snufkin.descend(babies);
        snufkin.putPot(this);
        snufkin.smoke(babies);
    }
    public void bringPot(int amount){
        amountOfPots += amount;
    }

    class Clock {
        private TraitsOfClock status;

        Clock(TraitsOfClock status) {
            this.status = status;
        }

        @Override
        public String toString() {
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

            @Override
            public String toString() {
                if(Math.random() >= 0.5) {
                    clock.setStatus(TraitsOfClock.WORKING);
                } else {
                    clock.setStatus(TraitsOfClock.STOPPED);
                }
                return " стояли " + this.getStatus() + " цветы ";
            }
        }
    }
}
