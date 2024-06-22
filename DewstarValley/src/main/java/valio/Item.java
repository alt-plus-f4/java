package valio;

public abstract class Item implements IUsableItem {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void use(Farm farm, Vector2 position);
    public abstract void use(Farm farm, Vector2 position, Vector2 direction, int powerLevel);
}