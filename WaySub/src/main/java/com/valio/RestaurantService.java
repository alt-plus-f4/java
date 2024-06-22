package com.valio;

import java.util.Scanner;

public class RestaurantService {
    private final Restaurant restaurant;
    private final Scanner scanner;

    public RestaurantService(Restaurant restaurant, Scanner scanner) {
        this.restaurant = restaurant;
        this.scanner = scanner;
    }

    public void addCook() {
        System.out.print("Enter cook name: ");
        String name = scanner.nextLine();
        try {
            restaurant.addCook(new Cook(name, restaurant));
            System.out.println("Cook " + name + " added successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addOven() {
        System.out.print("Enter oven name: ");
        String name = scanner.nextLine();
        try {
            restaurant.addOven(new Oven(name, restaurant), restaurant);
            System.out.println("Oven " + name + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createOrder() {
        System.out.print("Enter order type (1: ClassicHam, 2: LongBurger, 3: VeggieDelight): ");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter bread type (1: WHITE, 2: WHOLE_GRAIN): ");
        int breadType = Integer.parseInt(scanner.nextLine());
        Bread bread = breadType == 1 ? Bread.WHITE_BREAD : Bread.WHOLE_GRAIN_BREAD;

        Order order;

        switch (type) {
            case 1:
                order = Order.createClassicHam(bread);
                break;
            case 2:
                order = Order.createLongBurger(bread);
                break;
            case 3:
                order = Order.createVeggieDelight(bread);
                break;
            default:
                System.out.println("Invalid order type.");
                return;
        }
        restaurant.addOrder(order);
        System.out.println("Order created: " + order);
    }

    public void checkOrderStatus() {
        System.out.print("Enter order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Order order = restaurant.getOrderStatus(id);
        if (order != null) {
            System.out.println("Order " + id + " is completed: " + order);
        } else {
            System.out.println("Order " + id + " is not completed yet.");
        }
    }

    public void exitProgram() {
        try {
            restaurant.shutdown();
        } catch (InterruptedException e) {
            System.out.println("Error while shutting down: " + e.getMessage());
        }
        System.out.println("Program terminated.");
    }
}
