import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/input.txt");
        FileInputStream inputStream = new FileInputStream(file);
        Graph graph = Graph.fromStream(inputStream);
    }
}
