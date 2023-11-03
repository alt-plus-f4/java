import java.util.Random;

public class Game{
    private int[][] map;
    private GuiHandler guiHandler;
    private int score = 0;

    public Game(){
        this.map = new int[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                this.map[i][j] = getRandomTile();
            }
        }

        checkClear();

        guiHandler = new GuiHandler(this.map, this);
    }

    private void explodeBomb(int x, int y){
        System.out.println("BOOM");

        int startX = Math.max(0, x - 1);
        int startY = Math.max(0, y - 1);
        int endX = Math.min(this.map.length - 1, x + 1);
        int endY = Math.min(this.map[0].length - 1, y + 1);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if(this.map[i][j] != -1){
                    score += 1;
                    System.out.println(score);
                    this.map[i][j] = -1;
                }
            }
        }
    }

    public int getRandomTile() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        if (randomNumber < 30) {
            return 0;
        } else if (randomNumber < 60) {
            return 1;
        } else if (randomNumber < 90) {
            return 2;
        } else {
            return 3;
        }
    }

    public void checkClear() {
        for (int i = 0; i < 6; i++) {
            for(int j = 1; j < 5; j++){
                if(this.map[i][j] != -1 && this.map[i][j - 1] == this.map[i][j] && this.map[i][j] == this.map[i][j + 1]){
                    this.map[i][j - 1] = this.map[i][j] = this.map[i][j + 1] = -1;
                    score += 3;
                }
            }
//                (0,(1,(2),(3),4),5)
        }
    }

    public int moveTile(int x1, int y1, int x2, int y2){
        if(x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > 5 || x2 > 5 || y1 > 5 || y2 > 5){
//            System.out.println("Out of bounds");
            return 1;
        }
        else {
            int x = x1 - x2;
            int y = y1 - y2;
            int difference1 = Math.abs(x);
            int difference2 = Math.abs(y);

            if(difference1 > 1 || difference2 > 1){
                System.out.println("Should be right next to each other to switch them!");
                return 1;
            }

            if(this.map[y1][x1] == 3)
                explodeBomb(y2, x2);
            if(this.map[y2][x2] == 3)
                explodeBomb(y1, x1);

            int temp = this.map[y1][x1];
            this.map[y1][x1] = this.map[y2][x2];
            this.map[y2][x2] = temp;

            checkClear();
            System.out.println(score);
            if(score > 27)
                return 69;

            return 0;
        }
    }

    public int[][] getMap(){
        return this.map;
    }

    public void updateGUI(int[][] newMap) {
        guiHandler.updateGUI(newMap);
    }

    public static void main(String[] args) {
        Game CandyCrush = new Game();
    }
}

