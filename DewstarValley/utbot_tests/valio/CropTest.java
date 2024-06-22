package valio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CropTest {

    @Test
    void processNextDay() {
        Crop crop = new Crop("Wheat", 5);
        assertFalse(crop.isMature());
        assertEquals(0, crop.getAgeInDays());

        for (int i = 0; i < 3; i++) {
            crop.processNextDay();
            assertFalse(crop.isMature());
            assertEquals(i + 1, crop.getAgeInDays());
        }

        for (int i = 0; i < 2; i++) {
            crop.processNextDay();
        }
        assertTrue(crop.isMature());
        assertEquals(5, crop.getAgeInDays());
    }

    @Test
    void applyTool() {
        Crop crop = new Crop("Wheat", 5);
        crop.applyTool(null);
        assertFalse(crop.isMature());
    }

    @Test
    void harvest() {
        Crop crop = new Crop("Wheat", 1);
        crop.processNextDay();
        crop.harvest();
        assertEquals(false, crop.isMature());
    }

    @Test
    void getName() {
        Crop crop = new Crop("Wheat", 5);
        assertEquals("Wheat", crop.getName());
    }

    @Test
    void getAgeInDays() {
        Crop crop = new Crop("Wheat", 5);
        assertEquals(0, crop.getAgeInDays());
        crop.processNextDay();
        assertEquals(1, crop.getAgeInDays());
    }

    @Test
    void getDaysToMature() {
        Crop crop = new Crop("Wheat", 5);
        assertEquals(5, crop.getDaysToMature());
    }

    @Test
    void isMature() {
        Crop crop = new Crop("Wheat", 5);
        assertFalse(crop.isMature());
        crop.processNextDay();
        assertFalse(crop.isMature());
        for (int i = 0; i < 4; i++) {
            crop.processNextDay();
        }
        assertTrue(crop.isMature());
    }
}
