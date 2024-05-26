package com.valio;

import java.util.*;
import java.util.concurrent.*;

public class Restaurant {
    private final List<Cook> cooks = new ArrayList<>();
    private final List<Oven> ovens = new ArrayList<>();
    private final BlockingQueue<Order> waitingOrders = new LinkedBlockingQueue<>();
    private final BlockingQueue<Order> ovenQueue = new LinkedBlockingQueue<>();
    private final Map<Integer, Order> completedOrders = new ConcurrentHashMap<>();
    private final ExecutorService cookExecutor = Executors.newCachedThreadPool();
    private final ExecutorService ovenExecutor = Executors.newCachedThreadPool();

    public synchronized void addCook(Cook cook) throws IllegalArgumentException {
        for (Cook c : cooks) {
            if (c.getName().equals(cook.getName())) {
                throw new IllegalArgumentException("A cook with the same name already exists.");
            }
        }
        cooks.add(cook);
        cookExecutor.submit(cook);
        assignOrdersToCooks();
    }

    public synchronized void addOven(Oven oven, Restaurant restaurant) throws IllegalArgumentException {
        for (Oven o : ovens) {
            if (o.getName().equals(oven.getName())) {
                throw new IllegalArgumentException("An oven with the same name already exists.");
            }
        }
        ovens.add(oven);
        ovenExecutor.submit(oven);

        if (!ovenQueue.isEmpty()) {
            try {
                oven.bakeOrder(ovenQueue.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted while delegating order to oven");
            }
        }
    }

    public void addOrder(Order order) {
        waitingOrders.add(order);
        assignOrdersToCooks();
    }

    public void finalizeOrder(Order order) {
        System.out.println("Completed order " + order.getId());
        completedOrders.put(order.getId(), order);
    }

    public void delegateToOven(Order order, Cook cook) throws InterruptedException {
        for (Oven oven : ovens) {
            if (oven.retrieveBakedOrder() != null) {
                cook.completeOrder(order);
                return;
            }
        }
        for (Oven oven : ovens) {
            if (oven.retrieveBakedOrder() == null) {
                oven.bakeOrder(order);
                waitingOrders.remove(order);
                return;
            }
        }
    }

    public Order getOrderStatus(int id) {
        return completedOrders.get(id);
    }

    public void shutdown() throws InterruptedException {
        for(Cook cook : cooks)
            cook.addOrder(Order.POISON_PILL);
        for(Oven oven : ovens)
            oven.bakeOrder(Order.POISON_PILL);

        cookExecutor.shutdown();
        ovenExecutor.shutdown();
    }

    private void assignOrdersToCooks() {
        for (Cook cook : cooks) {
            if (waitingOrders.isEmpty()) break;
            if (cook.isAvailable()) {
                Order order = waitingOrders.poll();
                cook.addOrder(order);
            }
        }
    }

    public void addOrderToOven(Order order) throws InterruptedException {
        for (Oven oven : ovens) {
            if (oven.isAvailable()) {
                oven.bakeOrder(order);
                return;
            }
        }
        ovenQueue.add(order);
    }

    public List<Cook> getCooks() {
        return cooks;
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public BlockingQueue<Order> getWaitingOrders() {
        return waitingOrders;
    }

    public BlockingQueue<Order> getOvenQueue() {
        return ovenQueue;
    }

    public ExecutorService getCookExecutor() {
        return cookExecutor;
    }

    public ExecutorService getOvenExecutor() {
        return ovenExecutor;
    }

    public Map<Integer, Order> getCompletedOrders() {
        return completedOrders;
    }
}
