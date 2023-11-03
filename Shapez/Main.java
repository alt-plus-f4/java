public class Main {
    public static void main(String[] args){
        Shape shape = new Shape();
        System.out.println(shape.toString()); // Default shape

        //
        // Generates random segments for a figure
        //

        Mine mine = new Mine();
        shape = mine.pull(); // Random generated shape
        System.out.println(shape.toString());

        //
        // Rotation machine
        // Rotates the segments in a figure clockwise or counterclockwise
        //

        RotationMachine rotate = new RotationMachine(true);
        rotate.push(shape);
        shape = rotate.pull(); // Rotated shape

        rotate.changeRotation(); // Rotate the other way
        shape = rotate.pull();

        //
        // Cutting machine
        // Makes the right portion of a figure empty if horizontal
        // Makes the bottom portion of a figure empty if vertical
        //

        CuttingMachine knife = new CuttingMachine(true);
        knife.push(shape);
        shape = knife.pull(); // Rotated shape

        shape = mine.pull(); // Reset the shape
        knife.push(shape);

        knife.changeRotation(); // Rotate the other way
        shape = knife.pull();

    }
}
