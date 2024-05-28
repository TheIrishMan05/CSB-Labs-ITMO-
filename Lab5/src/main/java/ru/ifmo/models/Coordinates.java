package ru.ifmo.models;

import ru.ifmo.utils.Validatable;

import java.util.Objects;

public class Coordinates implements Validatable {
    private Long x;  //Максимальное значение поля: 901, Поле не может быть null
    private float y;

    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x +
                "," + y + ",";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean validate() {
        return (x <= 901 && x != null);
    }
}
