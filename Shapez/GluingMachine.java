class GluingMachine implements Machine {
    private Shape input;
    private boolean bisItIn = false;


    @Override
    public void push(Shape shape) {
        this.input = shape;
        bisItIn = true;
        System.out.println("Pushed shape GLUE:\n" + shape.toString());
    }

    @Override
    public Shape pull() {
        if(!bisItIn)
            return null;

        // TODO DO LEFT JOIN OR FILL THE EMPTY SPACES


        return null;
    }
}