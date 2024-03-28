package valio;

import java.util.Vector;

public class Vector2 extends Vector  {
    private int x;
    private int y;

    Vector2(){
        this.x = 0;
        this.y = 0;
    }

    Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Vector2 up    = new Vector2(0, 1);
    public static Vector2 down  = new Vector2(0, -1);
    public static Vector2 left  = new Vector2(-1 , 0);
    public static Vector2 right = new Vector2(1, 0);
    public static Vector2 zero = new Vector2(0, 0);
}

