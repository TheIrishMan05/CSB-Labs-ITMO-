package ru.ifmo.models;

import ru.ifmo.utils.Validatable;

public class Location implements Validatable {

    private long x;
    private int y;
    private String name; //Длина строки не должна быть больше 992, Поле может быть null

    public Location(long x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public String toString() {
        return x +
                "," + y +
                "," + name;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean validate() {
        return name == null || name.length() <= 992;
    }
}