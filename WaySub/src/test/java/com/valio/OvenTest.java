package com.valio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OvenTest {
    private Oven oven;

    @BeforeEach
    void setUp() {
        Restaurant restaurant = new Restaurant();
        oven = new Oven("Test Oven", restaurant);
    }

    @Test
    void getName() {
        assertEquals("Test Oven", oven.getName());
    }

    @Test
    void bakeOrder() throws InterruptedException {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        oven.bakeOrder(order);
        assertFalse(oven.isAvailable());
    }

    @Test
    void isAvailable() {
        assertTrue(oven.isAvailable());
    }
}