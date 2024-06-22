package valio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void addItem() {
        Inventory inventory = new Inventory();
        Item item1 = new Instrument("Hoe");
        Item item2 = new BagOfSeeds("Corn Seeds", 5, TypesOfSeeds.SEED);

        inventory.addItem(item1);
        inventory.addItem(item2);

        assertEquals(item1, inventory.getItem(0));
        assertEquals(item2, inventory.getItem(1));

        for (int i = 0; i < 8; i++) {
            inventory.addItem(new BagOfSeeds("Seeds" + i, 5, TypesOfSeeds.SEED));
        }
        assertThrows(IllegalStateException.class, () -> inventory.addItem(new BagOfSeeds("Extra Seeds", 5, TypesOfSeeds.SEED)));
    }

    @Test
    void getItem() {
        Inventory inventory = new Inventory();
        Item item1 = new Instrument("Hoe");
        Item item2 = new BagOfSeeds("Corn Seeds", 5, TypesOfSeeds.SEED);

        inventory.addItem(item1);
        inventory.addItem(item2);

        assertEquals(item1, inventory.getItem(0));
        assertEquals(item2, inventory.getItem(1));

        assertThrows(IllegalArgumentException.class, () -> inventory.getItem(-1));
        assertThrows(IllegalArgumentException.class, () -> inventory.getItem(2));
    }

    @Test
    void removeItem() {
        Inventory inventory = new Inventory();
        Item item1 = new Instrument("Hoe");
        Item item2 = new BagOfSeeds("Corn Seeds", 5, TypesOfSeeds.SEED);

        inventory.addItem(item1);
        inventory.addItem(item2);

        inventory.removeItem(item1);

        assertEquals(item2, inventory.getItem(0));
        assertThrows(IllegalArgumentException.class, () -> inventory.getItem(1));
        inventory.removeItem(new Instrument("Shovel"));

        assertEquals(inventory.getSize(), 1);
    }
}
