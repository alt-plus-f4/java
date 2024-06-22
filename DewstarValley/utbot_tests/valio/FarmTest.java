package valio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FarmTest {

    @Test
    void getCell() {
        Farm farm = new Farm();
        Vector2 validPosition = new Vector2(0, 0);
        Cell cell = farm.getCell(validPosition);
        assertNotNull(cell);
        assertEquals(TypeOfCells.EMPTY, cell.getState());
        assertEquals(validPosition.getX(), cell.getPosition().getX());
        assertEquals(validPosition.getY(), cell.getPosition().getY());

        assertThrows(IllegalArgumentException.class, () -> farm.getCell(new Vector2(-1, 0)));
        assertThrows(IllegalArgumentException.class, () -> farm.getCell(new Vector2(0, -1)));
        assertThrows(IllegalArgumentException.class, () -> farm.getCell(new Vector2(10, 0)));
        assertThrows(IllegalArgumentException.class, () -> farm.getCell(new Vector2(0, 10)));
    }

    @Test
    void processNextDayForAll() {
        Farm farm = new Farm();
        assertFalse(farm.isProcessingNextDay());

        Crop crop = new Crop("Wheat", 5);
        farm.getCell(new Vector2(0, 0)).setCrop(crop);
        assertEquals(0, crop.getAgeInDays());

        farm.processNextDayForAll();
        assertEquals(1, crop.getAgeInDays());
    }

    @Test
    void isProcessingNextDay() {
        Farm farm = new Farm();
        assertFalse(farm.isProcessingNextDay());

        farm.processNextDayForAll();
        assertFalse(farm.isProcessingNextDay());
    }
}
