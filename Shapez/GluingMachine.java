class GluingMachine implements Machine {
    private Shape inputLeft;
    private Shape inputRight;
    private int bisItIn = 0;

    @Override
    public void push(Shape shape) {
        if(bisItIn == 0)
            inputLeft = shape;
        else
            inputRight = shape;

        if(bisItIn == 0)
            bisItIn++;

        System.out.println("Pushed shape JOIN:\n" + shape.toString());
    }

    @Override
    public Shape pull() {
        if(bisItIn < 1)
            return null;
        System.out.println("ёsd");

        Shape output = new Shape(inputRight);

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(inputLeft.getSegments()[i][j] != Shape.Colors.Empty)
                    output.setSegment(j, i, inputLeft.getSegments()[i][j]);
            }
        }

        System.out.println("PULLED shape JOIN:\n" + output.toString());

        return output;
    }
}