package IA;

import model.*;
import tree.Node;

import java.util.List;

public class Alphabeta extends Algorithm implements Player {


    public Alphabeta(int who, String name){
        super(who,name);
    }

    @Override
    public double run(Node node, int depth, int level) {
        Node.init();
        return alphaBeta(node, Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,depth, level);
    }


    private double alphaBeta(Node node, double alpha, double beta, int depth, int level){
        node.generateChild();
        //Leaf
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            node.setWeight(Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece(), level));
            return node.getWeight();
        }
        int i = 1;
        //MAX
        if(node.isMax()){
            List<Node> succNode = node.getNodes();
            while(alpha < beta && i <= node.getNodes().size()){
                alpha = Double.max(alpha, alphaBeta(succNode.get(i-1),alpha,beta,depth-1, level));
                i++;
            }
            node.setWeight(alpha);
            return alpha;
        }
        //MIN
        else{
            List<Node> succNode = node.getNodes();
            while(alpha < beta && i <= node.getNodes().size()){
                beta = Double.min(beta, alphaBeta(succNode.get(i-1),alpha,beta,depth-1, level));
                i++;
            }
            node.setWeight(beta);
            return beta;
        }
    }
    @Override
    public String toString() {
        return "AlphaBeta";
    }
}
