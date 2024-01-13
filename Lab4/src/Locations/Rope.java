package Locations;

import Enums.ObjectStatus;

public class Rope {
    private Clothes[] clothes = new Clothes[5];
    private ObjectStatus status;
    private int durability = 100;

    public Rope(ObjectStatus status) {
        this.clothes[0] = new Clothes("тряпка");
        this.status = status;
    }

    public Clothes[] getClothes() {
        return clothes;
    }

    public void setClothes(Clothes[] clothes) {
        this.clothes = clothes;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        if (this.durability >= 0) {
            this.durability = durability;
        }
    }

    public ObjectStatus getStatus() {
        return status;
    }

    public void setStatus(ObjectStatus status) {
        this.status = status;
    }

    public static class Clothes {
        private final String name;

        public Clothes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }
}
