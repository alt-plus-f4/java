class CuttingMachine implements Machine {
    private Shape input;
    private boolean horizontalCut, bisItIn = false, brightOutput = false;

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
        Shape output = new Shape();

        if(!brightOutput){
            Shape.Colors[][] left = new Shape.Colors[2][2];

            // Copy the contents from input.getSegments() to left and right

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    left[i][j] = segments[i][j];
                }
            }

            if(horizontalCut){
                left[0][0] = Shape.Colors.Empty;
                left[0][1] = Shape.Colors.Empty;
                System.out.println("Horizontal");
            }
            else{
                left[0][0] = Shape.Colors.Empty;
                left[1][0] = Shape.Colors.Empty;
                System.out.println("Vertical");
            }

            output.setSegments(left);
            System.out.println("Pulled shape CUTTING MACHINE L:\n" + output.toString());
            brightOutput = true;
        }
        else{
            Shape.Colors[][] right = new Shape.Colors[2][2];

            // Copy the contents from input.getSegments() to left and right

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    right[i][j] = segments[i][j];
                }
            }

            if(horizontalCut){
                right[1][0] = Shape.Colors.Empty;
                right[1][1] = Shape.Colors.Empty;
                System.out.println("Horizontal");
            }
            else{
                right[0][1] = Shape.Colors.Empty;
                right[1][1] = Shape.Colors.Empty;
                System.out.println("Vertical");
            }


            output.setSegments(right);
            System.out.println("Pulled shape CUTTING MACHINE R:\n" + output.toString());
            brightOutput = false;
        }

        return output; // TODO FIND A WAY TO RETURN 2
    }


}