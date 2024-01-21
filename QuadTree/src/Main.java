import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree(-1000, 1000, -1000, 1000);
        List<Figure> figures = readFiguresFromFile("input.txt");

        for (Figure figure : figures)
            quadTree.addFigure(figure);

        List<Pair<Figure, Figure>> overlaps = quadTree.findOverlaps();

        for (Pair<Figure, Figure> overlap : overlaps)
            System.out.println(overlap.getFirst().getName() + "(" + overlap.getFirst().getCenter().getX() + ", "
            + overlap.getFirst().getCenter().getY() + ") - " + overlap.getSecond().getName() + "("
            + overlap.getSecond().getCenter().getX() + ", " + overlap.getSecond().getCenter().getY() + ")");
    }

    private static List<Figure> readFiguresFromFile(String filePath) {
        List<Figure> figures = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String type = tokens[0];
                double x = Double.parseDouble(tokens[1]);
                double y = Double.parseDouble(tokens[2]);

                Figure figure;
                switch (type) {
                    case "square":
                        double side = Double.parseDouble(tokens[3]);
                        figure = new Square(x, y, side);
                        break;
                    case "rectangle":
                        double length = Double.parseDouble(tokens[3]);
                        double width = Double.parseDouble(tokens[4]);
                        figure = new Rectangle(x, y, length, width);
                        break;
                    case "circle":
                        double radius = Double.parseDouble(tokens[3]);
                        figure = new Circle(x, y, radius);
                        break;
                    case "trapezoid":
                        double base1 = Double.parseDouble(tokens[3]);
                        double base2 = Double.parseDouble(tokens[4]);
                        double height = Double.parseDouble(tokens[5]);
                        figure = new Trapez(x, y, base1, base2, height);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid figure type: " + type);
                }

                figures.add(figure);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return figures;
    }
}
