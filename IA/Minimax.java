package IA;

import model.Couple;
import tree.Node;

//Ajouter méthode de génération dans minimax
public class Minimax {
    /**
     * Calcul la valeur minimax d'un à partir d'un <b>node</b> en tant que racine d'un
     * arbre de profondeur <b>depth</b>
     * @param depth
     * @param node
     * @return la valeur minimax de l'arbre de racine <b>node</b> et de profondeur <b>depth</b>
     */
    public double minimax(final int depth, Node node) {
        node.generateChild();
        //Leaf
       if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            node.setWeight(Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece()));
            return node.getWeight();
        }
        double val;
        //Max
        if(node.isMax()){
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
