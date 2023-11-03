import javax.lang.model.type.NullType;

class RotationMachine implements Machine {
    private Shape input;
    private boolean brotate; // true -> rotates left; false -> rotates right
    private boolean bisItIn = false;


    public RotationMachine(boolean rotateLeft) {
        this.brotate = rotateLeft;
    }

    @Override
    public void push(Shape shape) {
        this.input = shape;
        bisItIn = true;
        System.out.println("Pushed shape ROTATION:\n" + shape.toString());
    }

    @Override
    public Shape pull() {
        if(!bisItIn) try {
            throw new Exception("First push a figure to pull a figure out... ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Shape.Colors[][] segments = input.getSegments();

        Shape.Colors temp = segments[0][0];

        if(brotate){
            segments[0][0] = segments[1][0];
            segments[1][0] = segments[1][1];
            segments[1][1] = segments[0][1];
            segments[0][1] = temp;
        }
        else{
            segments[0][0] = segments[0][1];
            segments[0][1] = segments[1][1];
            segments[1][1] = segments[1][0];
            segments[1][0] = temp;
        }

        input.setSegments(segments);
        System.out.println("Pulled shape ROTATION:\n" + input.toString());

        return input;
    }

    public void changeRotation(){
        brotate = !brotate;
    }
}
