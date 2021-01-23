package edu.epam.multitrade.state.impl;

import edu.epam.multitrade.entity.Truck;
import edu.epam.multitrade.state.State;

public class UnloadingState extends State {
    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     *
     * @param truck
     */
    public UnloadingState(Truck truck) {
        super(truck);
    }

    @Override
    public String loading() {
        throw new UnsupportedOperationException();
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
        String state=truck.toString();
        return state;
    }
}
