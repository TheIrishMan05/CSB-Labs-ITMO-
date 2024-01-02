package Location;

import Humans.Snufkin;

public class Street implements ILocation
{

    @Override
    public void describe() {
        System.out.println("На улице " + Weather.RAINY);
    }

    @Override
    public void doActivities(Snufkin snufkin) {
        snufkin.knock();
        snufkin.open();
    }

}
