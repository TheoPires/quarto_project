package IA;

import model.Couple;
import tree.Node;

public class Negamax {
    /**
     * Compute the negamax value from a tree with <b>node</b> as root and depth of <b>depth</b>
     * @param depth
     * @param node
     * @return the negamax value of a tree with <b>node</b> as root and depth of <b>depth</b>
     */
    public double negamax(final int depth, Node node){
        node.generateChild();
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            if(node.isMax())
                node.setWeight(Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));
            else
                node.setWeight(-Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));
            return node.getWeight();
        }
        double val;
        //node generate value
        val = Double.NEGATIVE_INFINITY;
        for(Node n : node.getNodes())
            val = Double.max(val, -negamax(depth-1,n));
        return val;
    }

}
