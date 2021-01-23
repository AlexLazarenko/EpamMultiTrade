package edu.epam.multitrade.state.impl;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.state.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServedState extends State {
    private static final Logger logger = LogManager.getLogger(ServedState.class);
    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     *
     * @param truck
     */
    public ServedState(Truck truck) {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void served() {
        logger.info("state is "+truck.toString());
    }

    @Override
    public void unloading() {
        throw new UnsupportedOperationException();
    }
}
