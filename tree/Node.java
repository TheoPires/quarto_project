package tree;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private static int numberGenerate = 1;

    //Attributes
    private int number;
    private int who;
    private Board boardConfig;
    private Move moveTodo;
    private Double weight;
    private List<Node> nodes;
    private Node parent;

    //Constructors

    public Node(int who, Node parent, Board board, Move move) {
        this.number = numberGenerate++;
        this.who = who;
        this.boardConfig = board;
        this.moveTodo = move;
        this.nodes = new ArrayList<>();
        this.parent = parent;
    }

    public Node(int who, Node parent, Board board) {
        this.number = numberGenerate++;
        this.who = who;
        this.boardConfig = board;
        this.nodes = new ArrayList<>();
        this.parent = parent;
    }


    //Getter
    public boolean isLeaf(){
        return this.nodes.isEmpty();
    }

    public int getNumber() {
        return this.number;
    }

    public Double getWeight() {
        return this.weight;
    }

    public int getWho() {
        return this.who;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public Board getBoard() {
        return this.boardConfig;
    }

    public Move getMove() {
        return this.moveTodo;
    }

    public boolean isMax() {
        return this.getWho() > 0;
    }

    public boolean isMin() {
        return this.getWho() < 0;
    }

    public Node getParent() {
        return this.parent;
    }

    //Setter
    public void setWeight(double weight) {
        this.weight = weight;
    }

    //Method
    /**
     * Ajoute en tant que fils le <b>node</b> donné en argument.
     * @param node
     */
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

    /**
     * Génère les fils d'un <b>node</b>.
     */
    public void generateChild() {
        List<Couple> listEmptyCell = boardConfig.getEmptyCell();
        List<Piece> listRemPieces = boardConfig.getPieces();
        for (int i = 0; i < listEmptyCell.size(); i++) {
            for (int j = 0; j < listRemPieces.size(); j++) {
                Board cloneBoard = boardConfig.copy();
                Move move = new Move(listEmptyCell.get(i).getX(), listEmptyCell.get(i).getY(), listRemPieces.get(j));
                cloneBoard.playMove(move);
                Node n = new Node(who * -1, this, cloneBoard, move);
                this.nodes.add(n);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[" + this.number + "]");
        if(!isLeaf()) {
            result.append(" -> {");
            for(Node n : this.nodes)
                result.append(n.toString());
            result.append("} ");
        }
        return result.toString();
    }
}
