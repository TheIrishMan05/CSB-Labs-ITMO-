package Humans;

import Location.*;


public class Snufkin extends Human{
    public Snufkin(String name, ILocation location, Status status) {
        super(name, location, status);
    }
    public void grabLogs(Storeroom storeroom)
    {
        storeroom.takeLogs(20);
        System.out.println(this.getName() + " взял дрова.");
    }

    public void grabPot(Storeroom storeroom)
    {
        storeroom.takePot(1);
        System.out.println(this.getName() + " взял горшок.");
    }
    public void putLogs(Kitchen kitchen){
        kitchen.putLogs(20);
        System.out.println(this.getName() + " положил дрова в печь.");

    }
    public void enterLocation(Room room){
        if (this.getStatus().equals(Status.ACTIVE) && (this.getLocation() instanceof Lobby
                || this.getLocation() instanceof Kitchen || this.getLocation() instanceof Storeroom))
        {
            this.setLocation(room);
        }
    }
    @Override
    public void moveToHuman(){

    }
}
