package valio;

public interface IUsableItem {
    void use(Farm farm, Vector2 position);
    void use(Farm farm, Vector2 position, Vector2 direction, int powerLevel);
}
