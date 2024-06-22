package com.valio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void createClassicHam() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        assertNotNull(order);
        assertEquals(7, order.getIngredients().size());
    }

    @Test
    void createLongBurger() {
        Order order = Order.createLongBurger(Bread.WHITE_BREAD);
        assertNotNull(order);
        assertEquals(6, order.getIngredients().size());
    }

    @Test
    void createVeggieDelight() {
        Order order = Order.createVeggieDelight(Bread.WHITE_BREAD);
        assertNotNull(order);
        assertEquals(6, order.getIngredients().size());
    }

    @Test
    void getId() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        assertEquals(3, order.getId());
    }

    @Test
    void getIngredients() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        assertTrue(order.getIngredients().contains(Bread.WHITE_BREAD));
        assertTrue(order.getIngredients().contains(Meat.HAM));
    }

    @Test
    void getSpecialRequests() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        assertTrue(order.getSpecialRequests().isEmpty());
    }

    @Test
    void isValid() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        assertTrue(order.isValid());
    }

    @Test
    void testToString() {
        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        SpecialRequest specialRequest = SpecialRequest.NO_BAKE;
        String expected = "Order{id=2, ingredients=[WHITE_BREAD, HAM, CHEESE, TOMATO, ONION, CUCUMBER, MAYO], specialRequests=[]}";
        assertEquals(expected, order.toString());
    }
}