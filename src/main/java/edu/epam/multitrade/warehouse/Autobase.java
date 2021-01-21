package edu.epam.multitrade.warehouse;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.entity.TruckType;

import java.util.ArrayList;
import java.util.List;

public class Autobase {
    private List<Truck> autobase;
    private static final int maxTrucksOnBase = 6;
    private static final int minTrucksOnBase = 0;

    public Autobase() {
        autobase = new ArrayList<>();
    }
    public boolean addTruck(Truck truck){
        return true;
    }
    public Truck getTruck(TruckType type){
        return new Truck(1,TruckType.USUALDELIVERY);
    }
}
