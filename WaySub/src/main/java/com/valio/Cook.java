package com.valio;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable {
    private final String name;
    private final BlockingQueue<Order> ordersQueue = new LinkedBlockingQueue<>();
    private boolean available = true;
    private final Restaurant restaurant;

    public Cook(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void addOrder(Order order) {
        ordersQueue.add(order);
        available = false;
    }

    public void log(String msg) {
        System.out.println("[Cook " + name + "] " + msg);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = ordersQueue.take();
                if (order == Order.POISON_PILL) {
                    log("Received shutdown signal.");
                    break;
                }
                log("Starting order " + order.getId());
                for (Object ingredient : order.getIngredients()) {
                    Thread.sleep(ingredient instanceof Bread ? 1500 : 1200);
                    log("Added " + ingredient);
                }
                if (!order.getSpecialRequests().contains(SpecialRequest.NO_BAKE)) {
                    log("Passing order " + order.getId() + " to oven");
                    restaurant.addOrderToOven(order);
                } else {
                    log("Order " + order.getId() + " is ready (no bake)");
                    restaurant.finalizeOrder(order);
                }
                available = true;
                System.out.println("Cook " + name + " is available.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Interrupted");
        }
    }

    public boolean isOrderCompleted(Order order) {
        return ordersQueue.contains(order);
    }

    public void completeOrder(Order order) {
        ordersQueue.remove(order);
        available = true;
    }
}
