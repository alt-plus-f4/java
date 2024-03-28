package valio;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Item[] items;

    Inventory(){
        items = new Item[10];
    }

    void addItem(Item item) throws Exception {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getName().equals(item.getName())) {
                throw new Exception("Item already exists in the inventory");
            }
            if (items[i] == null) {
                items[i] = item;
                break;
            }
            if (i == items.length - 1) {
                throw new Exception("Inventory is full");
            }
        }
    }
}
