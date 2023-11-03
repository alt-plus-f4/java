import java.util.Random;

class Mine extends Machine {
    @Override
    public Shape pull() {
        Shape output = new Shape();
        return output;
    }

    @Override
    public void run() {
        Random random = new Random();
        int randomNumber = random.nextInt(5);
    }
}