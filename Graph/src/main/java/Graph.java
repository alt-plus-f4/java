import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Graph {
    private ArrayList<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node){
        if(node == null)
            throw new NullPointerException("Fuck you");
        nodes.add(node);
    }

    public Node getNodeAt(int index){
        return this.nodes.get(index);
    }

    public static Graph fromStream(InputStream in){
        Graph newGraph = new Graph();
        Scanner scanner = new Scanner(in);

        String[] nodeNames = scanner.nextLine().split(" ");
        for(String nodeName : nodeNames)
            newGraph.addNode(new Node(new ArrayList<>(), nodeName));

        for(int nodeIndex = 0; scanner.hasNextLine(); nodeIndex++) {
            String[] isConnected = scanner.nextLine().split(" ");
            for(int i = 0; i < isConnected.length; i++){
                if(isConnected[i].equals("1")) {
                    newGraph.nodes.get(nodeIndex).addEdge(newGraph.nodes.get(i));
                }
            }
        }

        System.out.println(newGraph);

        return newGraph;
    }

    void toFile(OutputStream out){

    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }
}
