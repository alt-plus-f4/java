package valio;

public class Cell {
    private TypeOfCells state;
    private Crop crop;
    private Vector2 position;
    private Farm farm;

    public Cell(TypeOfCells state, Vector2 position, Farm farm) {
        this.state = state;
        this.crop = null;
        this.position = position;
        this.farm = farm;
    }

    public TypeOfCells getState() {
        return state;
    }

    public void setState(TypeOfCells state) {
        this.state = state;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void applyTool(Item tool) {
        System.out.println("Applying tool to cell at position " + position.getX() + ", " + position.getY() + " with state " + state);
        if (state.equals(TypeOfCells.EMPTY)) {
            if (tool instanceof Hoe) {
                state = TypeOfCells.TILLED;
                System.out.println("Just tiled the cell at position " + position.getX() + ", " + position.getY());
            }
        }
        else if (state.equals(TypeOfCells.BLOCKED))throw new RuntimeException("Cannot apply tool to blocked cell.");
        else if (state.equals(TypeOfCells.TILLED)) throw new RuntimeException("Cannot apply tool to tilled cell.");
        else if (state.equals(TypeOfCells.PLANTEDSEED) || state.equals(TypeOfCells.PLANTEDTREE)) {
            if (tool instanceof Hoe) {
                if (state.equals(TypeOfCells.PLANTEDSEED)) {
                    state = TypeOfCells.EMPTY;
                    System.out.println("Just removed the seed from the cell at position " + position.getX() + ", " + position.getY());
                    setCrop(null);
                }
            }
            else if (tool instanceof Axe) {
                state = TypeOfCells.EMPTY;
                System.out.println("Just removed the tree from the cell at position " + position.getX() + ", " + position.getY());
                setCrop(null);
            }
            else if (tool instanceof Hand) {
                if(getCrop() != null) {
                    if (getCrop().harvest()) {
                        getCrop().setMature(false);
                        getCrop().setDaysToMature(3);
                        System.out.println("Just harvested the crop from the cell at position " + position.getX() + ", " + position.getY());
                        state = TypeOfCells.EMPTY;
                    }
                }
            }
        }
    }

    public synchronized void processNextDay() {
        if (crop != null) {
            crop.processNextDay();
        }
    }
}
