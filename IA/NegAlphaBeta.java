package IA;

import model.*;
import tree.Node;

import java.util.List;

public class NegAlphaBeta extends Algorithm implements Player {


    public NegAlphaBeta(int who, String name){
        super(who,name);
    }

    @Override
    public double run(Node node, int depth, int level) {
        Node.init();
        return negalphaBeta(node, Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,depth, level);
    }


    private double negalphaBeta(Node node, double alpha, double beta, int depth, int level){
        node.generateChild();
        //Leaf
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            node.setWeight(node.getWho() * Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece(), level));
            return node.getWeight();
        }
        int i = 1;
        double val = Double.NEGATIVE_INFINITY;
        List<Node> succNode = node.getNodes();

        while(alpha < beta && i <= node.getNodes().size()){
            val = Double.max(alpha, -negalphaBeta(succNode.get(i-1),-beta,-alpha,depth-1, level));
            alpha = Double.max(alpha, val);
            i++;
        }
        beta = Double.max(-alpha, -beta);
        node.setWeight(val);
        return val;
    }
    @Override
    public String toString() {
        return "NegAlphaBeta";
    }
}
