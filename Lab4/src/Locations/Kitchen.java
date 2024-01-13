package Locations;

import Entities.Babies;
import Entities.Snufkin;
import Enums.CloseAbleStatus;
import Enums.FurnaceStatus;
import Interfaces.CloseAble;

import java.util.Objects;

public class Kitchen extends Room {
    private final Furnace furnace = new Furnace(FurnaceStatus.COLD);
    private final Stove stove = new Stove(CloseAbleStatus.CLOSED);


    @Override
    public void describe() {
        System.out.println("На кухне " + furnace);
    }

    public void bringFirewood(int amount) {
        this.stove.setFirewoodInStove(this.stove.getFirewoodInStove() + amount);
    }


    public void doActivities(Snufkin snufkin, Storeroom.Firewood firewood, Babies babies) {
        this.describe();
        snufkin.putFirewood(this);
        snufkin.burnFirewood(this.stove, firewood, babies);
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

    public class Stove implements CloseAble {
        private int firewoodInStove = 0;
        private CloseAbleStatus shuffleStatus;

        Stove(CloseAbleStatus shuffleStatus) {
            this.shuffleStatus = shuffleStatus;
        }

        public int getFirewoodInStove() {
            return firewoodInStove;
        }

        public void setFirewoodInStove(int firewoodInStove) {
            if (this.firewoodInStove >= 0) {
                this.firewoodInStove = firewoodInStove;
            }
        }

        @Override
        public boolean isClosed() {
            return (this.getShuffleStatus().equals(CloseAbleStatus.CLOSED));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Stove stove = (Stove) o;
            return Objects.equals(shuffleStatus, stove.shuffleStatus);
        }

        @Override
        public int hashCode() {
            return Objects.hash(shuffleStatus);
        }

        public CloseAbleStatus getShuffleStatus() {
            return shuffleStatus;
        }

        public void setShuffleStatus(CloseAbleStatus shuffleStatus) {
            this.shuffleStatus = shuffleStatus;
        }
    }
}
