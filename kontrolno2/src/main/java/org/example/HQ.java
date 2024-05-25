package org.example;

public class HQ extends Building{
    private static int count = 0;

    public HQ(int positionX, int positionY){
        super(positionX, positionY, "HQ" + count++);
    }
}
