class RotationMachine extends Machine {
    private boolean rotateLeft;

    // 0 -> rotates right
    // 1 -> rotates left..
    public RotationMachine(boolean rotateLeft) {
        this.rotateLeft = rotateLeft;
    }

    @Override
    public void run() {
        Shape.Colors[][] segments = this.pull().getSegments();
        Shape.Colors temp = segments[0][0];
        segments[0][0] = segments[1][0];
        segments[1][0] = segments[1][1];
        segments[1][1] = segments[0][1];
        segments[0][1] = temp;
    }
}
