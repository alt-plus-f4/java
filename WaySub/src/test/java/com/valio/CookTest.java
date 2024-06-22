package com.valio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CookTest {
    private Cook cook;
    private Order order;

    @BeforeEach
    void setUp() {
        Restaurant restaurant = new Restaurant();
        cook = new Cook("Test Cook", restaurant);
        order = new Order(Arrays.asList(Bread.WHITE_BREAD, Meat.HAM, Cheese.CHEESE, Veggie.TOMATO, Veggie.ONION, Veggie.CUCUMBER, Sauce.MAYO), new ArrayList<>());
    }

    @Test
    void getName() {
        assertEquals("Test Cook", cook.getName());
    }

    @Test
    void isAvailable() {
        assertTrue(cook.isAvailable());
        cook.addOrder(order);
        assertFalse(cook.isAvailable());
    }

    @Test
    void addOrder() {
        cook.addOrder(order);
        assertFalse(cook.isAvailable());
    }

    @Test
    void isOrderCompleted() {
        cook.addOrder(order);
        assertTrue(cook.isOrderCompleted(order));
        cook.completeOrder(order);
        assertFalse(cook.isOrderCompleted(order));
    }

    @Test
    void completeOrder() {
        cook.addOrder(order);
        cook.completeOrder(order);
        assertTrue(cook.isAvailable());
    }
}