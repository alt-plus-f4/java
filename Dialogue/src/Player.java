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

    public int getAttribute(String attribute) {
        switch (attribute) {
            case "HP":
                return HP;
            case "STR":
                return STR;
            case "CHA":
                return CHA;
            case "GOLD":
                return GOLD;
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }

    public void addAttribute(String attribute, int value) {
        switch (attribute) {
            case "HP":
                HP += value;
                break;
            case "STR":
                STR += value;
                break;
            case "CHA":
                CHA += value;
                break;
            case "GOLD":
                GOLD += value;
                break;
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }
}