package Humans;

enum Traits {
    LOUD("громко"),
    ABRASIVELY("резко");


    private final String trait;

    Traits(String trait) {
        this.trait = trait;
    }

    @Override
    public String toString() {
        return trait;
    }
}
