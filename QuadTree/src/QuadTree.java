import java.util.ArrayList;
import java.util.List;

class QuadTree {
    int MAX_CAPACITY = 10;
    double minX, maxX, minY, maxY;
    List<Figure> figures;
    QuadTree[] children;

    public QuadTree(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.figures = new ArrayList<>();
        this.children = null;
    }

    public void addFigure(Figure figure) {
        if (children == null) {
            if (figures.size() < MAX_CAPACITY) {
                figures.add(figure);
            } else {
                split();
                for (Figure f : figures) {
                    distributeFigure(f);
                }
                figures = new ArrayList<>();
                distributeFigure(figure);
            }
        } else {
            distributeFigure(figure);
        }
    }

    private void split() {
        double midX = (minX + maxX) / 2;
        double midY = (minY + maxY) / 2;
        children = new QuadTree[4];
        children[0] = new QuadTree(minX, midX, minY, midY);
        children[1] = new QuadTree(midX, maxX, minY, midY);
        children[2] = new QuadTree(minX, midX, midY, maxY);
        children[3] = new QuadTree(midX, maxX, midY, maxY);
    }

    private void distributeFigure(Figure figure) {
        if (children != null) {
            for (QuadTree child : children) {
                if (isInside(child, figure)) {
                    child.addFigure(figure);
                    return;
                }
            }
        }
        figures.add(figure);
    }

    private boolean isInside(QuadTree quad, Figure figure) {
        return figure.center.x >= quad.minX && figure.center.x < quad.maxX &&
                figure.center.y >= quad.minY && figure.center.y < quad.maxY;
    }

    public List<Pair<Figure, Figure>> findOverlaps() {
        List<Pair<Figure, Figure>> overlaps = new ArrayList<>();
        findOverlapsRecursive(this, overlaps);
        return overlaps;
    }

    private void findOverlapsRecursive(QuadTree quad, List<Pair<Figure, Figure>> overlaps) {
        if (quad.children != null) {
            for (QuadTree child : quad.children) {
                findOverlapsRecursive(child, overlaps);
            }
        }
        for (int i = 0; i < quad.figures.size(); i++) {
            for (int j = i + 1; j < quad.figures.size(); j++) {
                Figure figure1 = quad.figures.get(i);
                Figure figure2 = quad.figures.get(j);
                if (figure1.overlaps(figure2)) {
                    overlaps.add(new Pair<>(figure1, figure2));
                }
            }
        }
    }
}