package Common;

import Location.ILocation;
import Location.Kitchen;
import Location.Lobby;
import Location.Storeroom;

import java.util.LinkedList;
import java.util.Queue;

public class House {
    public Queue<ILocation> createHouse() {
        Kitchen kitchen = new Kitchen();
        Lobby lobby = new Lobby();
        Storeroom storeroom = new Storeroom();
        Queue<ILocation> locations = new LinkedList<>();
        locations.add(lobby);
        locations.add(storeroom);
        locations.add(kitchen);
        locations.add(lobby);
        return locations;
    }

    public class Door {
        private ObjectStatus status;

        public Door(ObjectStatus status) {
            this.status = status;
        }

        public ObjectStatus getStatus() {
            return status;
        }

        public void setStatus(ObjectStatus status) {
            this.status = status;
        }

        public boolean knockable() {
            return this.getStatus().equals(ObjectStatus.CLOSED);
        }
    }
}
