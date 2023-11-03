class Machine {
    private Shape input, output;

    public void push(Shape shape) {
        this.input = shape;
        System.out.println("Pushed shape: " + shape.toString());
    }

    public Shape getInput(){
        return this.input;
    }

    public Shape pull() {
        System.out.println("Pulled shape: " + output.toString());
        return output;
    }

    public void run() {
    }
}
