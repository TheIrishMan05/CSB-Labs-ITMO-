import Common.House;
import Common.ObjectStatus;
import Humans.*;
import Location.*;

import java.util.LinkedList;
import java.util.Queue;

public class OneTwoThreeAction
{
    public static void main(String[] args)
    {
        House.Door door = new House.Door(ObjectStatus.CLOSED);
        Storeroom.Logs logs = new Storeroom.Logs("Дрова", TraitsOfLogs.NON_BURNING);
        House house = new House();
        Street street = new Street();
        Snufkin snufkin = new Snufkin("Снусмумрик", street, Status.ACTIVE);
        Babies babies = new Babies("Малыши", street, Status.ACTIVE);
        Queue<ILocation> scene = house.createHouse();
        street.doActivities(snufkin, door);
        for(ILocation i: scene){
            snufkin.enterLocation(i);
            if(i instanceof Lobby){
                ((Lobby) i).doActivities(snufkin, babies);
            } else if(i instanceof Kitchen){
                ((Kitchen) i).doActivities(snufkin, logs);
            } else {
                ((Storeroom) i).doActivities(snufkin);
            }
        }
    }
}