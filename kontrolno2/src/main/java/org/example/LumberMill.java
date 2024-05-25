package org.example;

public class LumberMill extends Building{
    private static int count = 0;

    public LumberMill(int positionX, int positionY){
        super(positionX, positionY, "LumberMill " + count++);
    }
}
