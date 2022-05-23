package IA;

import model.*;
import tree.Node;

public class Negamax extends Player implements Algorithm {

    public Negamax(int who){
        this.who = who;
    }

    @Override
    protected Move play(Board board, Piece selectedPiece) {
        Node n = new Node(who, null, board, DEPTH);
        double negamax = run(n,DEPTH);
        System.out.println("val :"+negamax);
        for(Node succ : n.getNodes()) {
            System.out.println(succ.getWeight()+" -> "+(succ.getMove() == null));
            if (succ.getWeight() == -negamax)
                return succ.getMove();
        }
        System.out.println("NOT OK");
        return null;
    }
    /**
     * Compute the negamax value from a tree with <b>node</b> as root and depth of <b>depth</b>
     * @param depth
     * @param node
     * @return the negamax value of a tree with <b>node</b> as root and depth of <b>depth</b>
     */
    @Override
    public double run(Node node, int depth) {
        Node.init();
        return negamax(node, depth);
    }
    private double negamax(Node node, final int depth){
        node.generateChild();
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            node.setWeight(node.getWho() * Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));
            return node.getWeight();
        }
        double val;
        //node generate value
        val = Double.NEGATIVE_INFINITY;
        for(Node n : node.getNodes()) {
            val = Double.max(val, -negamax(n, depth - 1));
        }
        node.setWeight(val);
        return val;
    }

    @Override
    public String toString() {
        return "Negamax";
    }

}
