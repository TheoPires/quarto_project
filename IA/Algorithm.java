package IA;

import model.BestMove;
import model.Board;
import model.Move;
import model.Piece;
import tree.Node;

public abstract class Algorithm {

    final int DEPTH = 2;
    private String name;
    protected int who;

    Algorithm(int who, String name){
        this.who = who;
        this.name = name;
    }

    public Move play(Board board, Piece selectedPiece, int level) {
        Node n = new Node(who, null, board, DEPTH);
        double value = run(n,DEPTH, level);
        Move m = null;
        double mWeight = Double.NEGATIVE_INFINITY;
        for(Node succ : n.getNodes()) {
            System.out.println(succ+" vs "+selectedPiece);
            if(succ.getMove().getPiece().equals(selectedPiece)) {
                if (m == null) {
                    m = succ.getMove();
                    mWeight = succ.getWeight();
                }else if(succ.getWeight() == value){
                    return succ.getMove();
                }else if(succ.getWeight() > mWeight){
                    m = succ.getMove();
                    mWeight = succ.getWeight();
                }
            }
        }
        return m;
    }

    public Piece selectPiece(Board board, int level){
        if(board.getPieces().size() == 0) return null;
        BestMove m = null;
        for(Piece p : board.getPieces()){
            BestMove newBestMove = Heuristic.calculateBestMove(board.copy(), p, level);
            if(m == null)
                m = newBestMove;
            else if(newBestMove.getWeight() < m.getWeight())
                m = newBestMove;
        }
        return m.getPiece();
    }

    abstract double run(Node node, int depth, int level);

    public String getName() {
        return name;
    }

}
