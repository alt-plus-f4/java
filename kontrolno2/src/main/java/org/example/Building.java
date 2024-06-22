package org.example;

public class Building extends GameEntity{
    private static int count = 0;

    public Building(int positionX, int positionY, String name){
        super(positionX, positionY, name + " " + count++);
    }
}
