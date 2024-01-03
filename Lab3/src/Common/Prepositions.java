package Common;

public enum Prepositions {
    IN("в"),
    TO("к"),
    ON("на"),
    FOR("за");
    private final String prep;

    Prepositions(String prep) {
        this.prep = prep;
    }

    @Override
    public String toString() {
        return prep;
    }
}
