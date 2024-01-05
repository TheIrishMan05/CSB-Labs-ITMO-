import Enums.ActiveStatus;
import Enums.ObjectStatus;
import Enums.Status;
import Enums.TraitsOfLogs;
import Entities.Babies;
import Entities.HouseCreator;
import Entities.Snufkin;
import Locations.*;
import Interfaces.*;
import java.util.Queue;

public class OneTwoThreeAction {
    public static void main(String[] args) {
        boolean visitLobby = false;
        House house = new House();
        HouseCreator builder = new HouseCreator();
        House.Door door = house.new Door(ObjectStatus.CLOSED);
        Street street = new Street();
        Storeroom.Logs logs = new Storeroom.Logs("Дрова", TraitsOfLogs.NON_BURNING);
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
                    ((Lobby) i).doActivities(snufkin, babies);
                }
            } else if (i instanceof Storeroom) {
                ((Storeroom) i).doActivities(snufkin);
            } else {
                ((Kitchen) i).doActivities(snufkin, logs, babies);
            }
        }
    }
}