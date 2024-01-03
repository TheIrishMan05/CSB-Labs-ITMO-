package Humans;


import Location.ILocation;
import Location.Kitchen;
import Location.Lobby;
import Location.Storeroom;

import java.util.Objects;

public abstract class Human implements LightUpAble,
        MoveAble, GrabAble, PutAble, DescendAble {
    private final String name;
    private ILocation location;
    private Status status;
    private ActiveStatus activeStatus;

    Human(String name, ILocation location, Status status, ActiveStatus activeStatus) {
        this.name = name;
        this.location = location;
        this.status = status;
        this.activeStatus = activeStatus;
    }

    public ActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getName() {
        return name;
    }

    public ILocation getLocation() {
        return location;
    }

    public void setLocation(ILocation location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean LightUp() {
        return Math.random() >= 0.5;
    }

    @Override
    public void putLogs(Kitchen kitchen) {
        kitchen.bringLogs(1);
    }

    @Override
    public void putPot(Lobby lobby) {
        lobby.bringPot((int) Math.round(Math.random() * 1));
    }

    @Override
    public void grabLogs(Storeroom storeroom) {
        storeroom.takeLogs(1);
    }

    @Override
    public void moveToHuman(Human human) {
        if (human.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println("Через некоторое время, " + this.getName() + " дошёл до " + human.getName());
            this.setStatus(Status.STATIC);
        }
    }

    @Override
    public void grabPot(Storeroom storeroom) {
        storeroom.takePot((int) Math.round(Math.random() * 1));
    }

    @Override
    public String toString() {
        return "Человек по имени " + this.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, status, activeStatus);
    }

    @Override
    public void descend(Human human) {
        if (human.getLocation() instanceof Lobby) {
            human.setStatus(Status.STATIC);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(location, human.location)
                && Objects.equals(status, human.status) && Objects.equals(activeStatus, human.activeStatus);
    }


}
