import java.util.ArrayList;
import java.util.List;

class Player {
    int HP;
    int STR;
    int CHA;
    int GOLD;
    List<String> inventory;

    public Player(int HP, int STR, int CHA, int GOLD) {
        this.HP = HP;
        this.STR = STR;
        this.CHA = CHA;
        this.GOLD = GOLD;
        this.inventory = new ArrayList<>();
    }
}