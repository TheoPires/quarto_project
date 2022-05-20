package IA;

import model.Couple;
import tree.Node;

//Ajouter méthode de génération dans minimax
public class Negamax {

    private Heuristic heuristic;

    public Negamax(Heuristic heuristic){
        this.heuristic = heuristic;
    }

    public double negamax(final int depth, Node node) {
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            return Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece());
        }
        double val;
        //node generate value
        val = Double.NEGATIVE_INFINITY;
        for(Node n : node.getNodes())
            val = Double.max(val, -negamax(depth-1,n));
        return val;
    }

}
