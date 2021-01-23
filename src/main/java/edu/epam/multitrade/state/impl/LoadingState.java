package edu.epam.multitrade.state.impl;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.state.State;

public class LoadingState extends State {
    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     *
     * @param truck
     */
    public LoadingState(Truck truck) {
        super(truck);
    }

    @Override
    public String loading() {
        String state=truck.toString();
        return state;
    }

    @Override
    public String onBase() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String outBase() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String served() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String unloading() {
        throw new UnsupportedOperationException();
    }
}
