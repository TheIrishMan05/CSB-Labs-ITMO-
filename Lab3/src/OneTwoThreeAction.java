import Humans.*;
import Location.*;

import java.util.LinkedList;
import java.util.Queue;

public class OneTwoThreeAction
{
    public static void main(String[] args)
    {
        Kitchen kitchen = new Kitchen();
        Street street = new Street();
        Lobby lobby = new Lobby();
        Storeroom storeroom = new Storeroom();
        Queue<Room> locations = new LinkedList<>();
        locations.add(lobby);
        locations.add(kitchen);
        locations.add(storeroom);
        Snufkin snufkin = new Snufkin("Снусмумрик", street, Status.ACTIVE);
        Babies babies = new Babies("Малыши", street, Status.ACTIVE);
        for(Room i: locations){
            snufkin.enterLocation(i);
            i.doActivities(snufkin);
        }

    }
}