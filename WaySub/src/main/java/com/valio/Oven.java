package com.valio;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Oven implements Runnable {
    private final String name;
    private final BlockingQueue<Order> bakingQueue = new LinkedBlockingQueue<>(1);
    private final BlockingQueue<Order> finishedQueue = new LinkedBlockingQueue<>();
    private final Restaurant restaurant;

    public Oven(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void bakeOrder(Order order) throws InterruptedException {
        bakingQueue.put(order);
    }

    public Order retrieveBakedOrder() throws InterruptedException {
        return finishedQueue.take();
    }

    public void log(String msg) {
        System.out.println("[Oven " + name + "] " + msg);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = bakingQueue.take();
                if (order == Order.POISON_PILL) {
                    log("Received shutdown signal.");
                    break;
                }
                log("Starting order " + order.getId());
                Thread.sleep(2000);
                log("Order " + order.getId() + " is ready");
                restaurant.finalizeOrder(order);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isAvailable() {
        return bakingQueue.isEmpty();
    }
}
