package org.example;

public class GoldStorage extends Building{
    private static int count = 0;

    public GoldStorage(int positionX, int positionY){
        super(positionX, positionY, "GoldStorage " + count++);
    }
}
