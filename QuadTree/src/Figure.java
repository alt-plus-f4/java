abstract class Figure {
    Point center;
    String name;

    public Figure(double x, double y) {
        this.center = new Point(x, y);
        this.name = generateName();
    }

    public abstract boolean overlaps(Figure other);

    public Point getCenter() {
        return center;
    }

    public String getName() {
        return name;
    }

    private String generateName() {
        return this.getClass().getSimpleName() + (int) (Math.random() * 100);
    }
}