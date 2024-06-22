package com.valio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {
    private RestaurantService restaurantService;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
    }

    @Test
    void addCook() {
        String input = "John Doe\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        restaurantService = new RestaurantService(restaurant, new Scanner(System.in));

        restaurantService.addCook();
        assertEquals(1, restaurant.getCooks().size());
        assertEquals("John Doe", restaurant.getCooks().get(0).getName());
    }

    @Test
    void addOven() {
        String input = "Test Oven\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        restaurantService = new RestaurantService(restaurant, new Scanner(System.in));

        restaurantService.addOven();
        assertEquals(1, restaurant.getOvens().size());
        assertEquals("Test Oven", restaurant.getOvens().get(0).getName());
    }

    @Test
    void createOrder() {
        String input = "1\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        restaurantService = new RestaurantService(restaurant, new Scanner(System.in));

        restaurantService.createOrder();
        assertEquals(1, restaurant.getWaitingOrders().size());
    }

    @Test
    void checkOrderStatus() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        restaurantService = new RestaurantService(restaurant, new Scanner(System.in));

        Order order = Order.createClassicHam(Bread.WHITE_BREAD);
        restaurant.finalizeOrder(order);
        restaurantService.checkOrderStatus();
        assertEquals(order, restaurant.getOrderStatus(order.getId()));
    }

    @Test
    void exitProgram() throws InterruptedException {
        restaurantService = new RestaurantService(restaurant, new Scanner(System.in));
        restaurantService.exitProgram();
        assertTrue(restaurant.getCookExecutor().isShutdown());
        assertTrue(restaurant.getOvenExecutor().isShutdown());
    }
}