import java.util.ArrayList;

public class Node {
    protected ArrayList<Node> edges;
    private String name;

    public Node(){
        this.edges = new ArrayList<>();
        this.name = "Unnamed Node";
    }

    public Node(ArrayList<Node> edges, String name) {
        this.edges = edges;
        this.name = name;
    }

    public void addEdge(Node edge){
        if(edge == null)
            throw new NullPointerException("Nah fuck you!");
        edge.addEdge(this);
        edges.add(edge);
    }

    public boolean isEdge(Node edge){
        return edges.contains(edge);
    }

    public ArrayList<Node> getEdges() {
        return edges;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "edges=" + edges +
                ", name=" + name +
                '}';
    }
}
