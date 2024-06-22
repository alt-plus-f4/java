import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Order {
    private static int counter = 1;
    private final int id;
    private final List<Object> ingredients;
    private final List<SpecialRequest> specialRequests;

    public Order(List<Object> ingredients, List<SpecialRequest> specialRequests) {
        this.id = counter++;
        this.ingredients = new ArrayList<>(ingredients);
        this.specialRequests = new ArrayList<>(specialRequests);
    }

    public static Order createClassicHam(Bread bread) {
        return new Order(Arrays.asList(bread, Meat.HAM, Cheese.CHEESE, Veggie.TOMATO, Veggie.ONION, Veggie.CUCUMBER, Sauce.MAYO), new ArrayList<>());
    }

    public static Order createLongBurger(Bread bread) {
        return new Order(Arrays.asList(bread, Meat.BEEF, Cheese.MELTED_CHEESE, Veggie.LETTUCE, Veggie.CUCUMBER, Sauce.BBQ), new ArrayList<>());
    }

    public static Order createVeggieDelight(Bread bread) {
        return new Order(Arrays.asList(bread, Cheese.CHEESE, Veggie.LETTUCE, Veggie.OLIVES, Veggie.TOMATO, Sauce.RANCH), new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public List<Object> getIngredients() {
        return ingredients;
    }

    public List<SpecialRequest> getSpecialRequests() {
        return specialRequests;
    }

    public boolean isValid() {
        if (ingredients.size() < 3) return false;
        if (!(ingredients.get(0) instanceof Bread)) return false;
        long meatCount = ingredients.stream().filter(i -> i instanceof Meat).count();
        long cheeseCount = ingredients.stream().filter(i -> i instanceof Cheese).count();
        if (meatCount > 1 || cheeseCount != 1) return false;
        long veggieCount = ingredients.stream().filter(i -> i instanceof Veggie).count();
        if (veggieCount < 1 || veggieCount > 3) return false;
        long sauceCount = ingredients.stream().filter(i -> i instanceof Sauce).count();
        return sauceCount >= 1 && sauceCount <= 3;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ingredients=" + ingredients +
                ", specialRequests=" + specialRequests +
                '}';
    }
}
