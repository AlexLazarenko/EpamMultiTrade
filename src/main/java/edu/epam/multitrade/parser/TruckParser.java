package edu.epam.multitrade.parser;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.entity.TruckDeliveryType;
import edu.epam.multitrade.entity.TruckWorkType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TruckParser {
    private static final String DELIMITER = "\\s+";

    public List<Truck> parseTrucks(List<String> lines) {
        int id;
        TruckDeliveryType deliveryType;
        TruckWorkType workType;
        Truck truck;
        List<Truck> truckList = new ArrayList<>();
        for (String line : lines) {
            List<String> trucks = new ArrayList<>(Arrays.asList(line.split(DELIMITER)));
            for (int i=0; i< trucks.size(); i=i+3) {
         //   for (String element : trucks) {
                id = Integer.parseInt(trucks.get(i));
                deliveryType = TruckDeliveryType.valueOf(trucks.get(i+1));
                workType = TruckWorkType.valueOf(trucks.get(i+2));
                truck = new Truck(id, deliveryType, workType);
                truckList.add(truck);
            }
        }
        return truckList;
    }
}
