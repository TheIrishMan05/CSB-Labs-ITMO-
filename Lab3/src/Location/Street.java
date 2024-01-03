package Location;

import Common.House;
import Humans.Human;
import Humans.Snufkin;

public class Street implements ILocation
{

    @Override
    public void describe() {
        System.out.println("На улице " + Weather.RAINY);
    }


    public void doActivities(Snufkin snufkin, House.Door door)  {
        snufkin.knock(door);
    }

}
