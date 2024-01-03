package Location;

import Humans.Human;
import Humans.Snufkin;

public class Kitchen extends Room
{
    private Furnace furnace;
    private int logsInStove = 0;
    @Override
    public void describe(){
        System.out.println("На кухне " + furnace.toString());
    }
    public void bringLogs(int amount){
        this.setLogsInStove(this.logsInStove + amount);
    }

    public int getLogsInStove() {
        return logsInStove;
    }

    public void setLogsInStove(int logsInStove) {
        if (this.logsInStove >= 0){
            this.logsInStove = logsInStove;
        }
    }

    public void doActivities(Snufkin snufkin, Storeroom.Logs logs) {
        snufkin.putLogs(this);
        snufkin.burnLogs(this, logs);
    }

    class Furnace
    {
        private Dish[] food = new Dish[3];
        private FurnaceStatus furnaceStatus;
        Furnace(FurnaceStatus status){
            this.furnaceStatus=status;
            this.food[0] = new Dish("пирог");
        }
        public FurnaceStatus getFurnaceStatus(){
            return furnaceStatus;
        }

        @Override
        public String toString() {
            return " стоит " + this.getFurnaceStatus() + " плита, на которой стоит " + food[0].getName();
        }

        class Dish
        {
            private String name;
            Dish(String name){
                this.name = name;
            }
            public String getName(){
                return name;
            }

        }
    }
}
