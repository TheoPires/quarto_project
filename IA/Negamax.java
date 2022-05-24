package IA;

import model.*;
import tree.Node;

public class Negamax extends Algorithm implements Player {

    public Negamax(int who, String name){
        super(who,name);
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
