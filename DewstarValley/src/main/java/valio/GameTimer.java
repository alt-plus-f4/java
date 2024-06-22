package valio;

public class GameTimer implements Runnable {
    private int day;
    private int seconds;
    private Farm farm;
    private PlayerInput playerInput;

    public GameTimer(Farm farm, PlayerInput playerInput) {
        this.farm = farm;
        this.playerInput = playerInput;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                seconds++;

                System.out.println("Day " + day + " Time " + seconds);

                if (seconds >= 5) {
                    System.out.println("Day ended. Processing next day...");

                    playerInput.disableInput();
                    farm.processNextDayForAll();

                    day++;
                    seconds = 0;

                    Thread.sleep(5000);
                    playerInput.enableInput();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
