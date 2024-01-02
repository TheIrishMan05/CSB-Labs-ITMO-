package Location;

import Humans.Snufkin;

public class Kitchen extends Room
{
    private Furnace furnace;
    private int logsInStove = 0;
    @Override
    public void describe(){
        System.out.println("На кухне " + furnace.toString());
    }
    public void putLogs(int amount){
        logsInStove += amount;
    }
    @Override
    public void doActivities(Snufkin snufkin) {
        snufkin.putLogs(this);
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
