package valio;

import java.util.Scanner;

public class PlayerInput extends Thread {
    private Inventory inventory;
    private boolean inputEnabled;
    private Scanner scanner;
    private Farm farm;

    public PlayerInput(Inventory inventory, Farm farm){
        this.inventory = inventory;
        this.inputEnabled = true;
        this.scanner = new Scanner(System.in);
        this.farm = farm;
    }

    public synchronized void disableInput() {
        inputEnabled = false;
    }

    public synchronized void enableInput() {
        inputEnabled = true;
        notify();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!inputEnabled) {
                    synchronized (this) {
                        wait();
                    }
                }
                System.out.println("Enter command: ");

                while (scanner.hasNextLine() && !inputEnabled) {
                    scanner.nextLine();
                }

                if (scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    processCommand(command);
                }
            } catch (Exception e) {
                System.err.println("Error processing input: " + e.getMessage());
            }
        }
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 5 || parts.length > 10) {
            System.err.println("Invalid command format.");
            return;
        }

        try {
            int index = Integer.parseInt(parts[1]);
            int posX = Integer.parseInt(parts[3]);
            int posY = Integer.parseInt(parts[4]);
            int dirX = 0;
            int dirY = 0;
            int powerLevel = 1;

            for (int i = 5; i < parts.length - 1; i += 2) {
                switch (parts[i]) {
                    case "D":
                        dirX = Integer.parseInt(parts[i + 1]);
                        dirY = Integer.parseInt(parts[i + 2]);
                        break;
                    case "L":
                        powerLevel = Integer.parseInt(parts[i + 1]);
                        break;
                    default:
                        System.err.println("Invalid command format.");
                        return;
                }
            }

            Item item = inventory.getItem(index);
            System.out.println("Item: " + item.getName());
            if (item != null) {
                if (item instanceof IUsableItem) {
                    IUsableItem usableItem = (IUsableItem) item;
                    usableItem.use(farm, new Vector2(posX, posY), new Vector2(dirX, dirY), powerLevel);
                } else {
                    System.err.println("Item is not usable.");
                }
            } else {
                System.err.println("Invalid item index.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.err.println("Invalid command parameters.");
        }
    }

}
