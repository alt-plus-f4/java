import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Restaurant restaurant = new Restaurant();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCook();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    checkOrderStatus();
                    break;
                case 4:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Add Cook");
        System.out.println("2. Create New Order");
        System.out.println("3. Check Order Status");
        System.out.println("4. Exit Program");
        System.out.print("Choose an option: ");
    }

    private static void addCook() {
        System.out.print("Enter cook's name: ");
        String name = scanner.nextLine();
        Cook cook = new Cook(name);
        try {
            restaurant.addCook(cook);
            System.out.println("Cook " + name + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createOrder() {
        System.out.print("Choose order type (1-Classic Ham, 2-Long Burger, 3-Veggie Delight, 4-Custom): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Order order;
        System.out.print("Enter bread type (1-White, 2-Whole Grain): ");
        int breadType = scanner.nextInt();
        scanner.nextLine();
        Bread bread = breadType == 1 ? Bread.WHITE_BREAD : Bread.WHOLE_GRAIN_BREAD;

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
            case 4:
                order = createCustomOrder(bread);
                break;
            default:
                System.out.println("Invalid order type.");
                return;
        }

        if (order.isValid()) {
            restaurant.addOrder(order);
            System.out.println("Order " + order.getId() + " added successfully.");
        } else {
            System.out.println("Invalid order. Please try again.");
        }
    }

    private static Order createCustomOrder(Bread bread) {
        List<Object> ingredients = new ArrayList<>();
        List<SpecialRequest> specialRequests = new ArrayList<>();

        ingredients.add(bread);

        System.out.print("Enter meat type (1-Ham, 2-Beef, 0-None): ");
        int meatType = scanner.nextInt();
        scanner.nextLine();
        if (meatType == 1) {
            ingredients.add(Meat.HAM);
        } else if (meatType == 2) {
            ingredients.add(Meat.BEEF);
        }

        System.out.print("Enter cheese type (1-Cheese, 2-Melted Cheese): ");
        int cheeseType = scanner.nextInt();
        scanner.nextLine();
        ingredients.add(cheeseType == 1 ? Cheese.CHEESE : Cheese.MELTED_CHEESE);

        System.out.print("Enter veggies (comma-separated, e.g., 1-Tomato, 2-Pepper, 3-Olives, 4-Lettuce, 5-Onion, 6-Cucumber): ");
        String[] veggieInputs = scanner.nextLine().split(",");
        for (String veggieInput : veggieInputs) {
            switch (Integer.parseInt(veggieInput.trim())) {
                case 1:
                    ingredients.add(Veggie.TOMATO);
                    break;
                case 2:
                    ingredients.add(Veggie.PEPPER);
                    break;
                case 3:
                    ingredients.add(Veggie.OLIVES);
                    break;
                case 4:
                    ingredients.add(Veggie.LETTUCE);
                    break;
                case 5:
                    ingredients.add(Veggie.ONION);
                    break;
                case 6:
                    ingredients.add(Veggie.CUCUMBER);
                    break;
                default:
                    System.out.println("Invalid veggie type.");
            }
        }

        System.out.print("Enter sauces (comma-separated, e.g., 1-Mayo, 2-BBQ, 3-Ranch): ");
        String[] sauceInputs = scanner.nextLine().split(",");
        for (String sauceInput : sauceInputs) {
            switch (Integer.parseInt(sauceInput.trim())) {
                case 1:
                    ingredients.add(Sauce.MAYO);
                    break;
                case 2:
                    ingredients.add(Sauce.BBQ);
                    break;
                case 3:
                    ingredients.add(Sauce.RANCH);
                    break;
                default:
                    System.out.println("Invalid sauce type.");
            }
        }

        System.out.print("Any special requests (1-No Bake, 0-None): ");
        int specialRequestInput = scanner.nextInt();
        scanner.nextLine();
        if (specialRequestInput == 1) {
            specialRequests.add(SpecialRequest.NO_BAKE);
        }

        return new Order(ingredients, specialRequests);
    }

    private static void checkOrderStatus() {
        System.out.print("Enter order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Order order = restaurant.getOrderById(id);
        if (order != null) {
            System.out.println("Order " + id + " has ingredients: " + order.getIngredients());
        } else {
            System.out.println("No such order.");
        }
    }

    private static void exitProgram() {
        try {
            restaurant.shutdown();
            System.out.println("Program is shutting down.");
        } catch (InterruptedException e) {
            System.out.println("Error shutting down the program.");
        }
    }
}
