package edu.epam.multitrade.state;

import edu.epam.multitrade.entity.Truck;

public abstract class State {
    protected Truck truck;

    /**
     * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
     * обращаться к его данным и методам в будущем, если потребуется.
     */
    public State(Truck truck) {
        this.truck = truck;
    }


    public abstract String loading();
    public abstract String onBase();
    public abstract String outBase();
    public abstract String served();
    public abstract String unloading();
}
