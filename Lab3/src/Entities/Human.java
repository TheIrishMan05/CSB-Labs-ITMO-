package Entities;


import Enums.ActiveStatus;
import Enums.Status;
import Locations.Lobby;
import Interfaces.*;
import java.util.Objects;

public abstract class Human implements LightUpAble,
        MoveAble, DescendAble, EnterLocationAble {
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
    public void moveToHuman(Human human) {
        if (human.getLocation().equals(this.getLocation())) {
            this.setStatus(Status.ACTIVE);
            System.out.println("Через некоторое время, " + this.getName() + " дошёл до " + human.getName());
            this.setStatus(Status.STANDING);
        }
    }
    @Override
    public void enterLocation(ILocation location)
    {
        if (this.getStatus().equals(Status.ACTIVE)){
            this.setLocation(location);
        }

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
        human.setStatus(Status.SITTING);
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
