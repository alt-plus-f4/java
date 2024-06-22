package valio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagOfSeedsTest {

    @Test
    void getSeedCount() {
        BagOfSeeds bag = new BagOfSeeds("Bag1", 5, TypesOfSeeds.SEED);
        assertEquals(5, bag.getSeedCount());
    }

    @Test
    void getSeedType() {
        BagOfSeeds bag = new BagOfSeeds("Bag2", 10, TypesOfSeeds.TREE);
        assertEquals(TypesOfSeeds.TREE, bag.getSeedType());
    }

    @Test
    void addSeeds() {
        BagOfSeeds bag = new BagOfSeeds("Bag3", 3, TypesOfSeeds.SEED);
        bag.addSeeds(2);
        assertEquals(5, bag.getSeedCount());
    }

    @Test
    void use() {
        Farm farm = new Farm();
        BagOfSeeds bag = new BagOfSeeds("Bag4", 1, TypesOfSeeds.SEED);
        Vector2 position = new Vector2(0, 0);
        assertThrows(IllegalStateException.class, () -> bag.use(farm, position));
    }
}
