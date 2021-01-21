package edu.epam.multitrade.entity;

public class Truck {
    private int size;
    private TruckType type;

    public Truck(int size, TruckType type) {
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TruckType getType() {
        return type;
    }

    public void setType(TruckType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (size != truck.size) return false;
        return type == truck.type;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "size=" + size +
                ", type=" + type +
                '}';
    }
}
