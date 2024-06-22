package valio;

public class Crop {
    private String name;
    private int ageInDays;
    private int daysToMature;
    private boolean isMature;

    public Crop(String name, int daysToMature) {
        this.name = name;
        this.ageInDays = 0;
        this.daysToMature = daysToMature;
        this.isMature = false;
    }

    public void processNextDay() {
        ageInDays++;
        if (ageInDays >= daysToMature) isMature = true;
    }

    public void applyTool(Item tool) {
        this.harvest();
    }

    public boolean harvest() {
        if (isMature) {
            System.out.println("Harvesting " + name);
            ageInDays = 0;
            this.isMature = false;
            return true;
        }
        else System.out.println(name + " is not mature yet." + " Days to mature: " + (daysToMature - ageInDays));
        return false;
    }

    public String getName() {
        return name;
    }

    public int getAgeInDays() {
        return ageInDays;
    }

    public int getDaysToMature() {
        return daysToMature;
    }

    public boolean isMature() {
        return isMature;
    }

    public void setAgeInDays(int ageInDays) {
        this.ageInDays = ageInDays;
    }

    public void setDaysToMature(int daysToMature) {
        this.daysToMature = daysToMature;
    }

    public void setMature(boolean mature) {
        isMature = mature;
    }
}