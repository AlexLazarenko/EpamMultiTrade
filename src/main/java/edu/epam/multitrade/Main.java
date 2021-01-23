package edu.epam.multitrade;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.parser.TruckParser;
import edu.epam.multitrade.reader.MultiTradeFileReader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MultiTradeFileReader reader=new MultiTradeFileReader();
        String dir="file.dir";
        List<String> stringTrucks=reader.readFromFile(dir);
        TruckParser parser=new TruckParser();
        List<Truck> listTrucks=parser.parseTrucks(stringTrucks);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Truck truck:listTrucks) {
            executorService.submit(truck);
        }
    }
}
