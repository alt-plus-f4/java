package valio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getState() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        assertEquals(TypeOfCells.EMPTY, cell.getState());
    }

    @Test
    void setState() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        cell.setState(TypeOfCells.TILLED);
        assertEquals(TypeOfCells.TILLED, cell.getState());
    }

    @Test
    void getCrop() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        assertNull(cell.getCrop());
    }

    @Test
    void setCrop() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        Crop crop = new Crop("Wheat", 5);
        cell.setCrop(crop);
        assertEquals(crop, cell.getCrop());
    }

    @Test
    void getPosition() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        assertEquals(position, cell.getPosition());
    }

    @Test
    void applyTool_Hoe_EmptyCell() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        Hoe hoe = new Hoe("S1mple Hoe");
        assertDoesNotThrow(() -> cell.applyTool(hoe));
        assertEquals(TypeOfCells.TILLED, cell.getState());
    }

    @Test
    void applyTool_Axe_PlantedSeedCell() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.PLANTEDSEED, position, farm);
        Axe axe = new Axe("Simple Axe");
        assertDoesNotThrow(() -> cell.applyTool(axe));
        assertEquals(TypeOfCells.EMPTY, cell.getState());
    }

    @Test
    void applyTool_Hand_PlantedTreeCell() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.PLANTEDTREE, position, farm);
        Hand hand = new Hand("Simple Hand");
        assertDoesNotThrow(() -> cell.applyTool(hand));
        assertEquals(TypeOfCells.PLANTEDTREE, cell.getState()); // не се маха
    }

    @Test
    void processNextDay_NoCropInCell() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.EMPTY, position, farm);
        assertDoesNotThrow(() -> cell.processNextDay());
        assertEquals(TypeOfCells.EMPTY, cell.getState());
    }

    @Test
    void processNextDay_CropInCell() {
        Farm farm = new Farm();
        Vector2 position = new Vector2(0, 0);
        Cell cell = new Cell(TypeOfCells.PLANTEDSEED, position, farm);
        Crop crop = new Crop("Wheat", 5);
        cell.setCrop(crop);
        assertDoesNotThrow(() -> cell.processNextDay());
        assertEquals(1, crop.getAgeInDays());
    }
}
