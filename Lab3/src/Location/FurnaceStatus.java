package Location;

public enum FurnaceStatus {
    COLD("остывшая"),
    HOT("горячая");
    private final String furnaceStatus;

    FurnaceStatus(String furnaceStatus) {
        this.furnaceStatus = furnaceStatus;
    }

    @Override
    public String toString() {
        return furnaceStatus;
    }
}
