package edu.epam.multitrade.entity;

import edu.epam.multitrade.state.State;
import edu.epam.multitrade.state.impl.OutBaseState;
import edu.epam.multitrade.warehouse.Autobase;

public class Truck implements Runnable{
    private int id;
    private TruckDeliveryType type;
    private TruckWorkType workType;
    private State state;
    private final Autobase autobase=Autobase.getInstance();

    public Truck(int id, TruckDeliveryType type, TruckWorkType workType) {
        this.id = id;
        this.type = type;
        this.workType = workType;
        this.state = new OutBaseState(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TruckDeliveryType getType() {
        return type;
    }

    public void setType(TruckDeliveryType type) {
        this.type = type;
    }

    public TruckWorkType getWorkType() {
        return workType;
    }

    public void setWorkType(TruckWorkType workType) {
        this.workType = workType;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (id != truck.id) return false;
        if (type != truck.type) return false;
        return workType == truck.workType;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        result = 31 * result + workType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", type=" + type +
                ", workType=" + workType +
                ", state=" + state +
                '}';
    }

    @Override
    public void run() {
        this.getState().outBase();
        autobase.addTruckToBase(this);
        autobase.addTruckToTerminal(this);
        autobase.terminalWork(this);
        autobase.removeTruckFromBase(this);
    }
}
