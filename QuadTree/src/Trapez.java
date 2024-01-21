class Trapez extends Figure {
    double base1, base2, height;

    public Trapez(double x, double y, double base1, double base2, double height) {
        super(x, y);
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
    }

    @Override
    public boolean overlaps(Figure other) {
        if (other instanceof Square) {
            Square square = (Square) other;

            double dx = Math.abs(center.x - square.center.x);
            double dy = Math.abs(center.y - square.center.y);

            if (dx > (square.side / 2 + base1 / 2) && dx > (square.side / 2 + base2 / 2)) return false;
            if (dy > (square.side / 2 + height / 2)) return false;
            if (dx <= square.side / 2 || dy <= square.side / 2) return true;

            double cornerDistance1 = Math.pow(dx - square.side / 2, 2) + Math.pow(dy - square.side / 2, 2);
            double cornerDistance2 = Math.pow(dx - base1 / 2, 2) + Math.pow(dy - height / 2, 2);
            double cornerDistance3 = Math.pow(dx - base2 / 2, 2) + Math.pow(dy - height / 2, 2);

            return cornerDistance1 <= Math.pow(square.side / 2, 2) || cornerDistance2 <= Math.pow(base1 / 2, 2) || cornerDistance3 <= Math.pow(base2 / 2, 2);
        }
        else if (other instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) other;

            double dx = Math.abs(center.x - rectangle.center.x);
            double dy = Math.abs(center.y - rectangle.center.y);

            if (dx > (rectangle.length / 2 + base1 / 2) && dx > (rectangle.length / 2 + base2 / 2)) return false;
            if (dy > (rectangle.width / 2 + height / 2)) return false;
            if (dx <= rectangle.length / 2 || dy <= rectangle.width / 2) return true;

            double cornerDistance1 = Math.pow(dx - rectangle.length / 2, 2) + Math.pow(dy - rectangle.width / 2, 2);
            double cornerDistance2 = Math.pow(dx - base1 / 2, 2) + Math.pow(dy - height / 2, 2);
            double cornerDistance3 = Math.pow(dx - base2 / 2, 2) + Math.pow(dy - height / 2, 2);

            return cornerDistance1 <= Math.pow(rectangle.length / 2, 2) || cornerDistance2 <= Math.pow(base1 / 2, 2) || cornerDistance3 <= Math.pow(base2 / 2, 2);
        }
        else if (other instanceof Circle) {
            Circle circle = (Circle) other;
            double dx = Math.abs(center.x - circle.center.x);
            double dy = Math.abs(center.y - circle.center.y);

            if (dx > (base1 / 2 + circle.radius) && dx > (base2 / 2 + circle.radius)) return false;
            if (dy > (height / 2 + circle.radius)) return false;
            if (dx <= base1 / 2 || dx <= base2 / 2 || dy <= height / 2) return true;


            double cornerDistance1 = Math.pow(dx - base1 / 2, 2) + Math.pow(dy - height / 2, 2);
            double cornerDistance2 = Math.pow(dx - base2 / 2, 2) + Math.pow(dy - height / 2, 2);

            return cornerDistance1 <= Math.pow(circle.radius, 2) || cornerDistance2 <= Math.pow(circle.radius, 2);
        }
        else if (other instanceof Trapez) {
            Trapez trapez = (Trapez) other;
            double dx = Math.abs(center.x - trapez.center.x);
            double dy = Math.abs(center.y - trapez.center.y);

            if (dx > (base1 / 2 + trapez.base1 / 2) && dx > (base2 / 2 + trapez.base2 / 2)) return false;
            if (dy > (height / 2 + trapez.height / 2)) return false;
            if (dx <= base1 / 2 || dx <= base2 / 2 || dy <= height / 2) return true;


            double cornerDistance1 = Math.pow(dx - base1 / 2, 2) + Math.pow(dy - height / 2, 2);
            double cornerDistance2 = Math.pow(dx - base2 / 2, 2) + Math.pow(dy - height / 2, 2);

            return cornerDistance1 <= Math.pow(trapez.base1 / 2, 2) || cornerDistance2 <= Math.pow(trapez.base2 / 2, 2);
        }
        return false;
    }
}