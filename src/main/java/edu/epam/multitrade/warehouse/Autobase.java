package edu.epam.multitrade.warehouse;

import edu.epam.multitrade.comparator.TruckDeliveryComparator;
import edu.epam.multitrade.entity.Truck;

import edu.epam.multitrade.entity.TruckWorkType;
import edu.epam.multitrade.state.impl.LoadingState;
import edu.epam.multitrade.state.impl.OnBaseState;
import edu.epam.multitrade.state.impl.ServedState;
import edu.epam.multitrade.state.impl.UnloadingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Autobase {
    private static final Logger logger = LogManager.getLogger(Autobase.class);
    private static Autobase instance;
    private static final AtomicBoolean instanceExist = new AtomicBoolean(false);
    private final PriorityQueue<Truck> trucksOnAutoBase;
    private final PriorityQueue<Truck> trucksOnTerminals;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();
    private static final int maxTrucksOnBase = 6;
    private static final int minTrucksOnBase = 0;
    private static final int maxTerminals = 3;
    private final TruckDeliveryComparator deliveryComparator=new TruckDeliveryComparator();

    public Autobase() {
        trucksOnAutoBase = new PriorityQueue<>(deliveryComparator);
        trucksOnTerminals = new PriorityQueue<>(deliveryComparator);
    }


    public static Autobase getInstance() {
        if (instanceExist.compareAndSet(false, true)) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Autobase();
                    instanceExist.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void addTruckToBase(Truck truck) {
        try {
            lock.lock();
            while (trucksOnAutoBase.size() == maxTrucksOnBase) {
                logger.info("{}: AutoBase is full, waiting", Thread.currentThread().getName());
                condition.await();
            }
            if (trucksOnAutoBase.size() < maxTrucksOnBase) {
                logger.info("Ready to go on AutoBase!");
                truck.changeState(new OnBaseState(truck));
                truck.getState().onBase();
                trucksOnAutoBase.add(truck);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addTruckToTerminal(Truck truck) {
        try {
            lock.lock();
            while (trucksOnTerminals.size() == maxTerminals) {
                logger.info("{}: Terminals is full, waiting", Thread.currentThread().getName());
                condition.await();
            }
            if (trucksOnTerminals.size() < maxTerminals) {
                logger.info("Ready to go on Terminal!");
                if (truck.getWorkType().equals(TruckWorkType.LOADING)){
                    truck.changeState(new LoadingState(truck));
                    truck.getState().loading();
                }else{
                    truck.changeState(new UnloadingState(truck));
                    truck.getState().unloading();
                }
                trucksOnTerminals.add(truck);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void removeTruckFromBase(Truck truck) {
        lock.lock();
        try {
            trucksOnTerminals.remove(truck);
            logger.info("{} : removed from terminal!", Thread.currentThread().getName());
            truck.changeState(new ServedState(truck));
            truck.getState().served();
            trucksOnAutoBase.remove(truck);
            logger.info("{} : removed from AutoBase!", Thread.currentThread().getName());
            if (trucksOnTerminals.isEmpty()) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void terminalWork(Truck truck) {
        logger.info("{} : working at terminal", Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
        }
    }
  /*
    private void discharging(Vehicle vehicle) {
        lock.lock();
        try {
            carsList.remove(vehicle);
            logger.info("{} : discharged!", Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(1000);
            if (carsList.isEmpty()) {
                currentState = FerryState.EMPTY;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }*/
/*
    private void sailing(Vehicle vehicle){
        logger.info("{} : cross the river!", Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
        }
    }*/
 /*   public Truck getTruck(int id) {
        return new Truck(1, 100, TruckDeliveryType.USUALDELIVERY, TruckWorkType.LOADING);
    }*/

 /*private void addCar(Vehicle vehicle) {
     try {
         lock.lock();
         while (currentState == FerryState.CHARGED) {
             logger.info("{}: all seats are occupied, waiting", Thread.currentThread().getName());
             condition.await();
         }
         if (!checkFreePlaceForVehicle(vehicle)) {
             logger.info("Ready to go!");
             currentState = FerryState.CHARGED;
             condition.signalAll();
             condition.await();
         }
         carsList.add(vehicle);
         logger.info("{}: boarded the ferry waiting for start.", Thread.currentThread().getName());
         condition.await(5000, TimeUnit.MILLISECONDS);
     } catch (InterruptedException e) {
         logger.error("InterruptedException: {}", e.getMessage());
     } finally {
         lock.unlock();
     }
 }*/
}
