class Square extends Figure {
    double side;

    public Square(double x, double y, double side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public boolean overlaps(Figure other) {
        if (other instanceof Square) {
            Square otherSquare = (Square) other;
            return Math.abs(center.x - otherSquare.center.x) < (side + otherSquare.side) / 2 && Math.abs(center.y - otherSquare.center.y) < (side + otherSquare.side) / 2;
        } else if (other instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) other;
            return Math.abs(center.x - rectangle.center.x) < (side + rectangle.length) / 2 && Math.abs(center.y - rectangle.center.y) < (side + rectangle.width) / 2;
        } else if (other instanceof Circle) {
            Circle circle = (Circle) other;
            double dx = Math.abs(center.x - circle.center.x);
            double dy = Math.abs(center.y - circle.center.y);

            if (dx > (side / 2 + circle.radius)) return false;
            if (dy > (side / 2 + circle.radius)) return false;
            if (dx <= side / 2 || dy <= side /2) return true;

            double cornerDistance = Math.pow(dx - side / 2, 2) + Math.pow(dy - side / 2, 2);
            return cornerDistance <= Math.pow(circle.radius, 2);

        } else if (other instanceof Trapez) {
            Trapez trapez = (Trapez) other;
            return Math.abs(center.x - trapez.center.x) < (side + trapez.base1 + trapez.base2) / 2 && Math.abs(center.y - trapez.center.y) < (side + trapez.height) / 2;
        }
        return false;
    }
}