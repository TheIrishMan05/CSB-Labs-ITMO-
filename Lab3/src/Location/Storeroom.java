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


    public void doActivities(Snufkin snufkin) {
        snufkin.grabLogs(this);
        snufkin.grabPot(this);

    }

    public Logs getLogs() {
        return logs;
    }

    public void takeLogs(int amount)
    {
        this.getLogs().quantity -= amount;
    }

    public void takePot(int amount)
    {
        this.countOfPots -= amount;
    }

    public static class Logs
    {
        int quantity = 100;
        private String name;
        private TraitsOfLogs traits;
        public Logs(String name, TraitsOfLogs traits)
        {
            this.name = name;
            this.traits = traits;
        }
        public String getName(){
            return name;
        }

        public TraitsOfLogs getTraits() {
            return traits;
        }

        public void setTraits(TraitsOfLogs traits) {
            this.traits = traits;
        }

        @Override
        public String toString(){
            return "лежали " + this.getName();
        }

    }
}
