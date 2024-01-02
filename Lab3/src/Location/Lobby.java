package Location;

import Humans.Babies;
import Humans.Snufkin;

public class Lobby extends Room {

    private Clock clock;
    private Table table;
    private Table.Flowers flowers;

    @Override
    public void describe() {
        System.out.println("В прихожей " + table.toString() + ", на котором " + flowers.toString() + ". На стене "
                + clock.toString());
    }

    @Override
    public void doActivities(Snufkin snufkin) {
        snufkin.
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
                return " стояли " + this.getStatus() + " цветы ";
            }
        }
    }
}
