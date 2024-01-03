package Humans;


import Location.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class Human implements LightUpAble,
        MoveAble, GrabAble, PutAble, DescendAble
{
    private ILocation location;
    private final String name;
    private Status status;
    Human(String name, ILocation location, Status status)
    {
        this.name = name;
        this.location = location;
        this.status = status;
    }
    public String getName()
    {
        return name;
    }
    public ILocation getLocation(){
        return location;
    }
    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public boolean LightUp()
    {
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
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
    public int hashCode(){
        return Objects.hash(name);
    }

    public void setLocation(ILocation location) {
        this.location = location;
    }
    @Override
    public void descend(Human human)
    {
        if (human.getLocation() instanceof Lobby){
            human.setStatus(Status.STATIC);
        }
    }


}
