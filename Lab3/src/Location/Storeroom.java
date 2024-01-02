package Location;

import Humans.Human;
import Humans.Snufkin;

public class Storeroom extends Room
{
    private Logs logs;
    private int countOfPots = 1;
    @Override
    public void describe() {
        System.out.println("В кладовке " + logs.toString() + ".");
    }

    @Override
    public void doActivities(Snufkin human) {
        human.grabLogs(this);
        human.grabPot(this);

    }

    public void takeLogs(int amount)
    {
        this.logs.quantity -= amount;
    }

    public void takePot(int amount)
    {
        this.countOfPots -= amount;
    }

    public class Logs
    {
        int quantity = 100;
        private String name;
        Logs(String name)
        {
            this.name = name;
        }
        public String getName(){
            return name;
        }
        @Override
        public String toString(){
            return "лежали " + this.getName();
        }

    }
}
