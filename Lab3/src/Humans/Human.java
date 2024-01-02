package Humans;


import Location.*;

import java.util.Objects;

public abstract class Human implements LightUpAble,
        MoveAble
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
    public void moveTo(Human human) {

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
}
