package IA;

import model.Couple;
import tree.Node;

//Ajouter méthode de génération dans minimax
public class Minimax {



    public double minimax(final int depth, Node node) {
        node.generateChild();
        /*System.out.println("nb fil : " + node.getNodes().size());
        System.out.println("nb case vide : "+node.getBoard().getEmptyCell());
        System.out.println("nb piece placer : "+node.getBoard().getPieces().size());*/
        //Leaf
       if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            node.setWeight(Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));
            //System.out.println("weight : " + node.getWeight());
            return node.getWeight();
        }
        double val;
        //Max
        if(node.getWho() > 0){
            val = Double.NEGATIVE_INFINITY;
            for(Node n : node.getNodes()) {
                val = Double.max(val, minimax(depth - 1, n));
            }
        }
        //Min
        else{
            val = Double.POSITIVE_INFINITY;
            for(Node n : node.getNodes())
                val = Double.min(val, minimax(depth - 1, n));


        }
        node.setWeight(val);
        return val;
    }

}
