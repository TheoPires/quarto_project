package tree;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class SSSNode {

    private static int numberGenerate = 1;

    //Attributes
    private int number;
    protected int who;
    protected Board boardConfig;
    private Move moveTodo;
    private Double weight;
    protected List<SSSNode> sssNodes;
    private SSSNode parent;
    private boolean isExplored;
    private int depth;

    //Constructors

    public SSSNode(int who, SSSNode parent, Board board, Move move, int depth) {
        this.number = numberGenerate++;
        this.who = who;
        this.boardConfig = board;
        this.moveTodo = move;
        this.sssNodes = new ArrayList<>();
        this.parent = parent;
        this.isExplored = false;
        this.depth = depth;
    }

    public SSSNode(Node node, int depth){
        this.number = numberGenerate++;
        this.who = node.getWho();
        this.boardConfig = node.getBoard();
        this.moveTodo = node.getMove();
        this.sssNodes = new ArrayList<>();
        this.parent = (node.getParent()== null)? null : new SSSNode(node.getParent(),depth);
        this.isExplored = false;
        this.depth  = depth;
    }


    //Getter
    public boolean isLeaf(){
        return this.sssNodes.isEmpty();
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

    public List<SSSNode> getSSSNodes() {
        return this.sssNodes;
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

    public boolean isExplored() {
        return isExplored;
    }

    public SSSNode getParent() {
        return this.parent;
    }

    public int getDepth() {
        return this.depth;
    }

    //Setter
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setExplored() {
        isExplored = true;
    }

    public void decreaseDepth(){
        if (depth > 0) {
            this.depth--;
        }
    }

    //Method
    /**
     * Ajoute en tant que fils le <b>node</b> donné en argument.
     * @param node
     */
    public void addSSSNode(SSSNode node) {
        if(node == null)
            throw new IllegalArgumentException("node must be not null.");
        this.sssNodes.add(node);
    }

    public boolean removeSSSNode(SSSNode node) {
        if(node == null)
            throw new IllegalArgumentException("node must be not null.");
        if(this.sssNodes.contains(node)){
            this.sssNodes.remove(node);
            return true;
        }else
            throw new IllegalArgumentException("node is not in");

    }

    /**
     * Génère les fils d'un <b>node</b>.
     */
    public void generateChild() {
        if(depth > 0) {
            List<Couple> listEmptyCell = boardConfig.getEmptyCell();
            List<Piece> listRemPieces = boardConfig.getPieces();
            for (int i = 0; i < listEmptyCell.size(); i++) {
                for (int j = 0; j < listRemPieces.size(); j++) {
                    Board cloneBoard = boardConfig.copy();
                    Move move = new Move(listEmptyCell.get(i).getX(), listEmptyCell.get(i).getY(), listRemPieces.get(j));
                    cloneBoard.playMove(move);
                    SSSNode n = new SSSNode(who * -1, this, cloneBoard, move, depth - 1);
                    this.sssNodes.add(n);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[" + this.number + "]");
        if(!isLeaf()) {
            result.append(" -> {");
            for(SSSNode n : this.sssNodes)
                result.append(n.toString());
            result.append("} ");
        }
        return result.toString();
    }
}
