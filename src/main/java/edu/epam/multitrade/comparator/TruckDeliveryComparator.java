package edu.epam.multitrade.comparator;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.entity.TruckDeliveryType;

import java.util.Comparator;

public class TruckDeliveryComparator implements Comparator<Truck> {

    @Override
    public int compare(Truck o1, Truck o2) {
        int returnValue;
        if ((o1.getType().equals(TruckDeliveryType.FASTDELIVERY)
                && o2.getType().equals(TruckDeliveryType.FASTDELIVERY))
                || (o1.getType().equals(TruckDeliveryType.USUALDELIVERY)
                && o2.getType().equals(TruckDeliveryType.USUALDELIVERY))) {
            returnValue = 0;
        } else if (o1.getType().equals(TruckDeliveryType.FASTDELIVERY)
                && o2.getType().equals(TruckDeliveryType.USUALDELIVERY)) {
            returnValue = 1;
        } else returnValue = -1;
        return returnValue;
    }
}
