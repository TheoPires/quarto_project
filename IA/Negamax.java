package IA;

import model.Couple;
import tree.Node;

//Ajouter méthode de génération dans minimax
public class Negamax {

    public double negamax(final int depth, Node node){
        node.generateChild();
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            if(node.getWho() > 0)
                node.setWeight(Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));
            else
                node.setWeight(-Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));

            System.out.println("weight: "+node.getWeight());
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
