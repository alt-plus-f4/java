package org.example;

public class GameEntity {
    private int positionX;
    private int positionY;
    private String name;

    private static int count = 0;

    public GameEntity(int positionX, int positionY, String name) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.name = name;
    }

    public GameEntity() {
        this(0, 0, "noname " + count++);
    }

    public String getName() {
        return name;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setName(String name) {
        this.name = name;
    }
}
