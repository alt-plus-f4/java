package valio;

public class Farm {
    private Cell[][] farmCells;
    private boolean processingNextDay;

    public Farm() {
        farmCells = new Cell[10][10];
        initializeFarmCells();
        processingNextDay = false;
    }

    private void initializeFarmCells() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                farmCells[i][j] = new Cell(TypeOfCells.EMPTY, new Vector2(j, i),this);
            }
        }
    }

    public Cell getCell(Vector2 position) {
        int x = position.getX();
        int y = position.getY();
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new IllegalArgumentException("Invalid cell position.");
        }
        return farmCells[x][y];
    }

    public synchronized void processNextDayForAll() {
        if (processingNextDay) return;
        processingNextDay = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                farmCells[i][j].processNextDay();
            }
        }
        processingNextDay = false;
    }

    public synchronized boolean isProcessingNextDay() {
        return processingNextDay;
    }
}