package Enums;

public enum TraitsOfFlowers {
    NOTFADED("ещё свежие"),
    FADED("увядшие");
    private final String traitOfFlowers;

    TraitsOfFlowers(String traitOfObjects) {
        this.traitOfFlowers = traitOfObjects;
    }

    @Override
    public String toString() {
        return traitOfFlowers;
    }
}
