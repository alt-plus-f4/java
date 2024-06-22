package valio;

public class Main {
    public static void main(String[] args) {
        Farm farm = new Farm();

        Inventory inventory = new Inventory();
        inventory.addItem(new Hoe("Hoe"));
        inventory.addItem(new BagOfSeeds("Carrot", 5, TypesOfSeeds.SEED));
        inventory.addItem(new BagOfSeeds("Tree", 5, TypesOfSeeds.TREE));
        inventory.addItem(new Hand("just a normal hand"));

        PlayerInput playerInput = new PlayerInput(inventory, farm);
        playerInput.start();

        GameTimer gameTimer = new GameTimer(farm, playerInput);
        Thread gameThread = new Thread(gameTimer);
        gameThread.start();
    }
}
