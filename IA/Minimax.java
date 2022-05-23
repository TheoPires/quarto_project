package IA;

import model.Couple;
import model.Player;
import tree.Node;

//Ajouter méthode de génération dans minimax
public class Minimax extends Player implements Algorithm {
    /**
     * Calcul la valeur minimax d'un à partir d'un <b>node</b> en tant que racine d'un
     * arbre de profondeur <b>depth</b>
     * @param depth
     * @param node
     * @return la valeur minimax de l'arbre de racine <b>node</b> et de profondeur <b>depth</b>
     */
    public double run(Node node, int depth){
        Node.init();
        return minimax(node,depth);
    }

    private double minimax(Node node, final int depth) {
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
                val = Double.max(val, minimax(n, depth - 1));
            }
        }
        //Min
        else{
            val = Double.POSITIVE_INFINITY;
            for(Node n : node.getNodes())
                val = Double.min(val, minimax(n, depth - 1));


        }
        node.setWeight(val);
        return val;
    }

}
