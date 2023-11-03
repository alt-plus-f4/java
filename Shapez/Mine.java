import java.util.Random;

class Mine implements Machine { // D
    @Override
    @Deprecated
    public void push(Shape shape) {
    }

    @Override
    public Shape pull() {
        Shape input = new Shape();

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++)
                input.setSegment(j, i, getRandomColor());
        }

        return input;
    }

    private Shape.Colors getRandomColor(){
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;
        System.out.println(randomNumber);
        return Shape.Colors.values()[randomNumber];
    }
}