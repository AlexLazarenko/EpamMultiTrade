package edu.epam.multitrade.state.impl;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.state.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutBaseState extends State {
    private static final Logger logger = LogManager.getLogger(OutBaseState.class);

    public OutBaseState(Truck truck){
        super(truck);
    }

    @Override
    public void loading() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onBase() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void outBase() {
       logger.info("state is "+truck.toString());
    }

    @Override
    public void served() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unloading() {
        throw new UnsupportedOperationException();
    }
}
