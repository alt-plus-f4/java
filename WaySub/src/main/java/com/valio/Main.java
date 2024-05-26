package com.valio;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Restaurant restaurant = new Restaurant();
    private static final RestaurantService restaurantService = new RestaurantService(restaurant, scanner);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add cook");
            System.out.println("2. Add oven");
            System.out.println("3. Create order");
            System.out.println("4. Check order status");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    restaurantService.addCook();
                    break;
                case 2:
                    restaurantService.addOven();
                    break;
                case 3:
                    restaurantService.createOrder();
                    break;
                case 4:
                    restaurantService.checkOrderStatus();
                    break;
                case 5:
                    restaurantService.exitProgram();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
