package Locations;

import Enums.FurnaceStatus;
import Entities.Babies;
import Entities.Snufkin;
public class Kitchen extends Room {
    private final Furnace furnace = new Furnace(FurnaceStatus.COLD);
    private int logsInStove = 0;

    @Override
    public void describe() {
        System.out.println("На кухне " + furnace.toString());
    }

    public void bringLogs(int amount) {
        this.setLogsInStove(this.getLogsInStove() + amount);
    }

    public int getLogsInStove() {
        return logsInStove;
    }

    public void setLogsInStove(int logsInStove) {
        if (this.logsInStove >= 0) {
            this.logsInStove = logsInStove;
        }
    }

    public void doActivities(Snufkin snufkin, Storeroom.Logs logs, Babies babies) {
        this.describe();
        snufkin.putLogs(this);
        snufkin.burnLogs(this, logs);
        snufkin.moveToHuman(babies);
    }

    class Furnace {
        private final Dish[] food = new Dish[3];
        private FurnaceStatus furnaceStatus;

        Furnace(FurnaceStatus status) {
            this.furnaceStatus = status;
            this.food[0] = new Dish("пирог");
        }

        public FurnaceStatus getFurnaceStatus() {
            return furnaceStatus;
        }

        public void setFurnaceStatus(FurnaceStatus furnaceStatus) {
            this.furnaceStatus = furnaceStatus;
        }

        @Override
        public String toString() {
            if (Math.random() >= 0.5) {
                setFurnaceStatus(FurnaceStatus.COLD);
            } else {
                setFurnaceStatus(FurnaceStatus.HOT);
            }
            return " стоит " + this.getFurnaceStatus() + " плита, на которой стоит " + food[0].getName();
        }

        class Dish {
            private final String name;

            Dish(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

        }
    }
}
