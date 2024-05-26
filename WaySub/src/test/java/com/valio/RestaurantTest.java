package com.valio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
    }

    @Test
    void addCook() {
        Cook cook = new Cook("John Doe", restaurant);
        restaurant.addCook(cook);
        assertEquals(1, restaurant.getCooks().size());
        assertEquals("John Doe", restaurant.getCooks().get(0).getName());
    }

    @Test
    void addOven() {
        Oven oven = new Oven("Test Oven", restaurant);
        restaurant.addOven(oven, restaurant);
        assertEquals(1, restaurant.getOvens().size());
        assertEquals("Test Oven", restaurant.getOvens().get(0).getName());
    }

    @Test
    void addOrder() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        restaurant.addOrder(order);
        assertEquals(1, restaurant.getWaitingOrders().size());
    }

    @Test
    void finalizeOrder() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        restaurant.finalizeOrder(order);
        assertEquals(1, restaurant.getCompletedOrders().size());
    }

    @Test
    void getOrderStatus() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        restaurant.finalizeOrder(order);
        assertEquals(order, restaurant.getOrderStatus(order.getId()));
    }

    @Test
    void shutdown() throws InterruptedException {
        Cook cook = new Cook("John Doe", restaurant);
        Oven oven = new Oven("Test Oven", restaurant);
        restaurant.addCook(cook);
        restaurant.addOven(oven, restaurant);
        restaurant.shutdown();
        assertTrue(restaurant.getCookExecutor().isShutdown());
        assertTrue(restaurant.getOvenExecutor().isShutdown());
    }

    @Test
    void addOrderToOven() throws InterruptedException {
        Oven oven = new Oven("Test Oven", restaurant);
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        restaurant.addOven(oven, restaurant);
        restaurant.addOrderToOven(order);
        assertFalse(oven.isAvailable());
    }
}