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
    private int depth;
    private Double weight;
    private List<Node> nodes;

    //Constructors

    public Node(int who, Board board, Move move) {
        this.number = numberGenerate++;
        this.who = who;
        this.boardConfig = board;
        this.moveTodo = move;
        this.nodes = new ArrayList<>();
    }

    public Node(int who, Board board) {
        this.number = numberGenerate++;
        this.who = who;
        this.boardConfig = board;
        this.nodes = new ArrayList<>();
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

    //Setter

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String result = "["+this.number+"]";
        if(!isLeaf()) {
            result += " -> {";
            for(Node n : this.nodes)
                result += n.toString();
            result += "} ";
        }
        return result;
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
     * @throws CloneNotSupportedException
     */
    public void generateChild() throws CloneNotSupportedException{
        List<Couple> listEmptyCell = boardConfig.getEmptyCell();
        List<Piece> listRemPieces = boardConfig.getPieces();
        for (int i = 0; i < listEmptyCell.size(); i++) {
            for (int j = 0; j < listRemPieces.size(); j++) {
                Board cloneBoard = (Board) this.getBoard().clone();
                Move move = new Move(listEmptyCell.get(i).getX(), listEmptyCell.get(i).getY(), listRemPieces.get(j));
                cloneBoard.playMove(move);
                this.nodes.add(new Node(who * -1, cloneBoard, move));
            }
        }
    }
}
