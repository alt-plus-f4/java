package valio;

import java.util.ArrayList;
import java.util.List;

class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item instanceof Instrument) {
            Instrument tool = (Instrument) item;
            for (Item existingItem : items) {
                if (existingItem instanceof Instrument && existingItem.getName().equals(tool.getName())) {
                    throw new IllegalArgumentException("Instrument with the same name already exists in the inventory.");
                }
            }
        } else if (item instanceof BagOfSeeds) {
            BagOfSeeds seedBag = (BagOfSeeds) item;
            for (Item existingItem : items) {
                if (existingItem instanceof BagOfSeeds && existingItem.getName().equals(seedBag.getName())) {
                    BagOfSeeds existingSeedBag = (BagOfSeeds) existingItem;
                    existingSeedBag.addSeeds(seedBag.getSeedCount());
                    return;
                }
            }
        }

        if (items.size() >= 10) {
            throw new IllegalStateException("Inventory is full.");
        }

        items.add(item);
    }

    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid item index.");
        }
        return items.get(index);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    public int getSize() {
        return items.size();
    }
}