class Circle extends Figure {
    double radius;

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public boolean overlaps(Figure other) {
        if (other instanceof Square) {
            Square square = (Square) other;
            double dx = Math.abs(center.x - square.center.x);
            double dy = Math.abs(center.y - square.center.y);

            if (dx > (square.side / 2 + radius)) return false;
            if (dy > (square.side / 2 + radius)) return false;
            if (dx <= square.side / 2 || dy <= square.side / 2) return true;


            double cornerDistance = Math.pow(dx - square.side / 2, 2) + Math.pow(dy - square.side / 2, 2);
            return cornerDistance <= Math.pow(radius, 2);
        }
        else if (other instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) other;
            double dx = Math.abs(center.x - rectangle.center.x);
            double dy = Math.abs(center.y - rectangle.center.y);

            if (dx > (rectangle.length / 2 + radius)) return false;

            if (dy > (rectangle.width / 2 + radius)) return false;


            if (dx <= rectangle.length / 2 || dy <= rectangle.width / 2) return true;

            double cornerDistance = Math.pow(dx - rectangle.length / 2, 2) + Math.pow(dy - rectangle.width / 2, 2);
            return cornerDistance <= Math.pow(radius, 2);
        }
        else if (other instanceof Circle) {
            Circle otherCircle = (Circle) other;
            double distanceBetweenCenters = Math.sqrt(Math.pow(center.x - otherCircle.center.x, 2) + Math.pow(center.y - otherCircle.center.y, 2));
            return distanceBetweenCenters < (radius + otherCircle.radius);
        }
        else if (other instanceof Trapez) {
            Trapez trapez = (Trapez) other;
            double dx = Math.abs(center.x - trapez.center.x);
            double dy = Math.abs(center.y - trapez.center.y);

            if (dx > (trapez.base1 / 2 + radius) && dx > (trapez.base2 / 2 + radius)) return false;
            if (dy > (trapez.height / 2 + radius)) return false;
            if (dx <= trapez.base1 / 2 || dx <= trapez.base2 / 2 || dy <= trapez.height / 2) return true;

            double cornerDistance1 = Math.pow(dx - trapez.base1 / 2, 2) + Math.pow(dy - trapez.height / 2, 2);
            double cornerDistance2 = Math.pow(dx - trapez.base2 / 2, 2) + Math.pow(dy - trapez.height / 2, 2);

            return cornerDistance1 <= Math.pow(radius, 2) || cornerDistance2 <= Math.pow(radius, 2);
        }
        return false;
    }
}