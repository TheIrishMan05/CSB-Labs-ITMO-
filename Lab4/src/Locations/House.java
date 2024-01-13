package Locations;

import Enums.CloseAbleStatus;
import Interfaces.CloseAble;

public class House {
    private final Lobby lobby = new Lobby();
    private final Kitchen kitchen = new Kitchen();
    private final Storeroom storeroom = new Storeroom();

    public Kitchen getKitchen() {
        return kitchen;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public Storeroom getStoreroom() {
        return storeroom;
    }

    public class Door implements CloseAble {
        private CloseAbleStatus status;

        public Door(CloseAbleStatus status) {
            this.status = status;
        }

        public CloseAbleStatus getStatus() {
            return status;
        }

        public void setStatus(CloseAbleStatus status) {
            this.status = status;
        }

        public boolean isClosed() {
            return this.getStatus().equals(CloseAbleStatus.CLOSED);
        }
    }
}
