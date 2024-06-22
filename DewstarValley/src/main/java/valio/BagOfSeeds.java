package valio;

import com.sun.source.tree.Tree;

class BagOfSeeds extends Item {
    private int seedCount;
    private TypesOfSeeds seedType;

    public BagOfSeeds(String name, int seedCount, TypesOfSeeds seedType) {
        super(name);
        this.seedCount = seedCount;
        this.seedType = seedType;
    }

    public int getSeedCount() {
        return seedCount;
    }

    public TypesOfSeeds getSeedType() {
        return seedType;
    }

    public void addSeeds(int count) {
        seedCount += count;
    }

    @Override
    public void use(Farm farm, Vector2 position) {
        Cell cell = farm.getCell(position);
        if(cell.getState().equals(TypeOfCells.TILLED)) {
            if (seedCount > 0) {
                if (seedType == TypesOfSeeds.SEED) cell.setState(TypeOfCells.PLANTEDSEED);
                else throw new IllegalStateException("You can only plant seeds in tiled grounds");
                cell.setCrop(new Crop(getName(), 3));
                seedCount--;
                System.out.println(getName() + " seed planted at " + position.getX() + ", " + position.getY() + ".");
            } else throw new IllegalStateException("No seeds left in the bag.");
        }
        else if (cell.getState().equals(TypeOfCells.EMPTY)) {
            if (seedCount > 0) {
                if (seedType == TypesOfSeeds.TREE) cell.setState(TypeOfCells.PLANTEDTREE);
                else throw new IllegalStateException("You can not plant seeds in empty grounds.");
                cell.setCrop(new Crop(getName(), 3));
                seedCount--;
                System.out.println(getName() + " tree planted at " + position.getX() + ", " + position.getY() + ".");
            } else throw new IllegalStateException("No seeds left in the bag.");
        }
        else throw new IllegalStateException("Cell is not empty.");
    }

    @Override
    public void use(Farm farm, Vector2 position, Vector2 direction, int powerLevel) {
        if (powerLevel < 1 || powerLevel > 3) {
            throw new IllegalArgumentException("Invalid power level.");
        }

        for (int i = 0; i < powerLevel; i++) {
            int newX = position.getX() + direction.getX() * i;
            int newY = position.getY() + direction.getY() * i;
            Vector2 newPosition = new Vector2(newX, newY);
            use(farm, newPosition);
        }
    }
}