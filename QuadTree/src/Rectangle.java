class Rectangle extends Figure {
    double length, width;

    public Rectangle(double x, double y, double length, double width) {
        super(x, y);
        this.length = length;
        this.width = width;
    }

    @Override
    public boolean overlaps(Figure other) {
        if (other instanceof Square) {
            Square square = (Square) other;
            return Math.abs(center.x - square.center.x) < (length + square.side) / 2 && Math.abs(center.y - square.center.y) < (width + square.side) / 2;
        } else if (other instanceof Rectangle) {
            Rectangle otherRectangle = (Rectangle) other;
            return Math.abs(center.x - otherRectangle.center.x) < (length + otherRectangle.length) / 2 && Math.abs(center.y - otherRectangle.center.y) < (width + otherRectangle.width) / 2;
        } else if (other instanceof Circle) {
            Circle circle = (Circle) other;
            double dx = Math.abs(center.x - circle.center.x);
            double dy = Math.abs(center.y - circle.center.y);

            if (dx > (length / 2 + circle.radius)) return false;
            if (dy > (width / 2 +  circle.radius)) return false;
            if (dx <= length / 2 || dy <= width / 2) return true;
            
            double cornerDistance = Math.pow(dx - length / 2, 2) + Math.pow(dy - width / 2, 2);
            return cornerDistance <= Math.pow(circle.radius, 2);
            
        } else if (other instanceof Trapez) {
            Trapez trapez = (Trapez) other;
            return Math.abs(center.x - trapez.center.x) < (length + trapez.base1 + trapez.base2) / 2 && Math.abs(center.y - trapez.center.y) < (width + trapez.height) / 2;
        }
        return false;
    }
}

