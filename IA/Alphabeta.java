package IA;

import model.Couple;
import tree.Node;

import java.util.List;

public class Alphabeta {


    private Heuristic heuristic;

    public Alphabeta(Heuristic heuristic){
        this.heuristic = heuristic;
    }

    public double alphaBeta(Node node, double alpha, double beta, int depth) throws CloneNotSupportedException{
        if (depth == 0 || node.isLeaf()) {
            Couple tmp = new Couple(node.getMove().getX(), node.getMove().getY());
            return Heuristic.calulateWeight(node.getBoard(),tmp,node.getMove().getPiece());
        }
        node.generateChild();
        int i = 1;
        if(node.getWho() > 0){
            List<Node> succNode = node.getNodes();
            while(alpha < beta && i < node.getNodes().size()){
                alpha = Double.max(alpha, alphaBeta(succNode.get(i),alpha,beta,depth-1));
                i++;
            }
            return alpha;
        }else{
            List<Node> succNode = node.getNodes();
            while(alpha < beta && i < node.getNodes().size()){
                beta = Double.min(beta, alphaBeta(succNode.get(i),alpha,beta,depth-1));
                i++;
            }
            return beta;
        }
    }
}
