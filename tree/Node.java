package tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private static int numberGenerate = 1;

    //Attributes
    private int number;
    private int weight;
    private List<Node> nodes;

    //Constructors
    public Node(){
        this.number = numberGenerate++;
        this.weight = 0;
        this.nodes = new ArrayList<>();
    }

    public Node(List<Node> nodes){
        if(nodes == null)
            throw new IllegalArgumentException("List of nodes must be not null.");
        this.number = numberGenerate++;
        this.weight = 0;
        this.nodes = nodes;
    }

    //Method
    public void addNode(Node node) {
        if(node == null)
            throw new IllegalArgumentException("node must be not null.");
        this.nodes.add(node);
    }

    public boolean removeNode(Node node) {
        if(node == null)
            throw new IllegalArgumentException("node must be not null.");
        if(this.nodes.contains(node)){
            this.nodes.remove(node);
            return true;
        }else
            throw new IllegalArgumentException("node is not in");

    }

    public boolean isLeaf(){
        return this.nodes.isEmpty();
    }

    //Getter

    public int getNumber() {
        return number;
    }

    public int getWeight() {
        return weight;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    //Setter

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String result = "["+this.number+"]";
        if(!isLeaf()) {
            result+= " -> {";
            for(Node n : this.nodes)
                result+= n.toString();
            result+="} ";
        }
        return result;

    }
}
