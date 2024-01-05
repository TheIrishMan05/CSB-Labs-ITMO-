package Enums;

public enum TraitsOfClock {
    WORKING("ещё работавшие"),
    STOPPED("остановившиеся");
    private final String traitOfClock;

    TraitsOfClock(String traitOfClock) {
        this.traitOfClock = traitOfClock;
    }

    @Override
    public String toString() {
        return traitOfClock;
    }
}
