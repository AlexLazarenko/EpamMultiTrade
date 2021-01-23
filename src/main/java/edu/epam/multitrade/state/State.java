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


    public abstract void loading();
    public abstract void onBase();
    public abstract void outBase();
    public abstract void served();
    public abstract void unloading();
}
