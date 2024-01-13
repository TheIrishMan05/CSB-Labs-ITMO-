import Entities.Babies;
import Entities.HouseCreator;
import Entities.Snufkin;
import Enums.*;
import Interfaces.ILocation;
import Locations.*;

import java.util.Queue;

public class OneTwoThreeAction {
    public static void main(String[] args) {
        boolean visitLobby = false;
        House house = new House();
        Rope rope = new Rope(ObjectStatus.LIVELONG);
        HouseCreator builder = new HouseCreator();
        House.Door door = house.new Door(CloseAbleStatus.CLOSED);
        Street street = new Street();
        Storeroom.Firewood firewood = new Storeroom.Firewood(TraitsOfFirewood.NON_BURNING);
        Storeroom.Pot pot = new Storeroom.Pot();
        Snufkin snufkin = new Snufkin("Снусмумрик", street, Status.ACTIVE, ActiveStatus.NO_SMOKING);
        Babies babies = new Babies("Малыши", street, Status.ACTIVE, ActiveStatus.NO_SMOKING);
        Queue<ILocation> scene = builder.createHouse(house);
        street.doActivities(snufkin, door);
        for (ILocation i : scene) {
            snufkin.enterLocation(i);
            if (i instanceof Lobby) {
                if (visitLobby == false) {
                    visitLobby = true;
                    babies.enterLocation(i);
                    snufkin.descend(babies);
                } else {
                    ((Lobby) i).doActivities(snufkin, babies, pot, rope);
                }
            } else if (i instanceof Storeroom) {
                ((Storeroom) i).doActivities(snufkin);
            } else {
                ((Kitchen) i).doActivities(snufkin, firewood, babies);
            }
        }
    }
}