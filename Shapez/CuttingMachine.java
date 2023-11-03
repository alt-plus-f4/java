class CuttingMachine implements Machine {
    private Shape input;
    private boolean horizontalCut, bisItIn = false;

    public CuttingMachine(boolean horizontalCut) {
        this.horizontalCut = horizontalCut;
    }

    public void changeRotation(){
        horizontalCut = !horizontalCut;
    }

    @Override
    public void push(Shape shape) {
        this.input = shape;
        bisItIn = true;
        System.out.println("Pushed shape CUTTING MACHINE:\n" + shape.toString());
    }

    @Override
    public Shape pull() {
        if(!bisItIn) return null;

        Shape.Colors[][] segments = input.getSegments();

        if(horizontalCut){ // TODO WAIT DISCORD
            segments[0][0] = Shape.Colors.Empty;
            segments[1][0] = Shape.Colors.Empty;
        }
        else{
            segments[0][0] = Shape.Colors.Empty;
            segments[0][1] = Shape.Colors.Empty;
        }

        input.setSegments(segments);
        System.out.println("Pulled shape CUTTING MACHINE:\n" + input.toString());

        return input;
    }

}