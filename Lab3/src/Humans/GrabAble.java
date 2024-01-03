package Humans;

import Location.ILocation;
import Location.Kitchen;
import Location.Storeroom;

public interface GrabAble
{
    void grabLogs(Storeroom storeroom);
    void grabPot(Storeroom storeroom);
}
