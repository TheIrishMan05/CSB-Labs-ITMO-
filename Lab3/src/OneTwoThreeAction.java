import Common.House;
import Common.ObjectStatus;
import Humans.ActiveStatus;
import Humans.Babies;
import Humans.Snufkin;
import Humans.Status;
import Location.*;

import java.util.Queue;

public class OneTwoThreeAction {
    public static void main(String[] args) {
        boolean visitLobby = false;
        House house = new House();
        House.Door door = house.new Door(ObjectStatus.CLOSED);
        Street street = new Street();
        Storeroom.Logs logs = new Storeroom.Logs("Дрова", TraitsOfLogs.NON_BURNING);
        Snufkin snufkin = new Snufkin("Снусмумрик", street, Status.ACTIVE, ActiveStatus.NO_SMOKING);
        Babies babies = new Babies("Малыши", street, Status.ACTIVE, ActiveStatus.NO_SMOKING);
        Queue<ILocation> scene = house.createHouse();
        street.doActivities(snufkin, door);
        for (ILocation i : scene) {
            snufkin.enterLocation(i);
            if (i instanceof Lobby) {
                if (visitLobby == false) {
                    visitLobby = true;
                    babies.setLocation(i);
                    ((Lobby) i).doActivities(snufkin, babies);
                } else {
                    snufkin.putPot((Lobby) i);
                    babies.moveToHuman(snufkin);
                }
            } else if (i instanceof Storeroom) {
                ((Storeroom) i).doActivities(snufkin);
            } else {
                ((Kitchen) i).doActivities(snufkin, logs, babies);
            }
        }
    }
}