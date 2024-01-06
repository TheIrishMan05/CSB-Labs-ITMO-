package Locations;

import Entities.Babies;
import Entities.Snufkin;
import Enums.FurnaceStatus;

public class Kitchen extends Room {
    private final Furnace furnace = new Furnace(FurnaceStatus.COLD);
    private int firewoodInStove = 0;

    @Override
    public void describe() {
        System.out.println("На кухне " + furnace);
    }

    public void bringLogs(int amount) {
        this.setLogsInStove(this.firewoodInStove + amount);
    }

    public int getLogsInStove() {
        return firewoodInStove;
    }

    public void setLogsInStove(int logsInStove) {
        if (this.firewoodInStove >= 0) {
            this.firewoodInStove = logsInStove;
        }
    }

    public void doActivities(Snufkin snufkin, Storeroom.Firewood firewood, Babies babies) {
        this.describe();
        snufkin.putFirewood(this);
        snufkin.burnFirewood(this, firewood);
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
