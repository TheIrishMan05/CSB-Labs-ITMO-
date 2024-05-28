package ru.ifmo.models;

import ru.ifmo.utils.Validatable;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Validatable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String name, LocalDate birthday, Color eyeColor, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday) && eyeColor == person.eyeColor && Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, location);
    }

    @Override
    public String toString() {
        return "," + name +
                "," + birthday +
                "," + eyeColor +
                "," + location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean validate() {
        if (name.isEmpty() || name == null) return false;
        else return (location.validate() || location == null);
    }
}
