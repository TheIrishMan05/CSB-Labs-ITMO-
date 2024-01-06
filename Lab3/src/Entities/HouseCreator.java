package Entities;

import Interfaces.ILocation;
import Locations.House;

import java.util.LinkedList;
import java.util.Queue;

public class HouseCreator {
    public Queue<ILocation> createHouse(House house) {
        Queue<ILocation> locations = new LinkedList<>();
        locations.add(house.getLobby());
        locations.add(house.getStoreroom());
        locations.add(house.getKitchen());
        locations.add(house.getLobby());
        System.out.println("Безымянный строитель построил дом.");
        return locations;
    }
}
