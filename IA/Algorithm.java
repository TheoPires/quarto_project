package IA;

import model.BestMove;
import model.Board;
import model.Move;
import model.Piece;
import tree.Node;

public abstract class Algorithm {

    final int DEPTH = 2;
    private String name;
    private int who;

    Algorithm(int who, String name){
        this.who = who;
        this.name = name;
    }

    public Move play(Board board, Piece selectedPiece) {
        Node n = new Node(who, null, board, DEPTH);
        double value = run(n,DEPTH);
        Move m = null;
        double mWeight = Double.NEGATIVE_INFINITY;
        for(Node succ : n.getNodes()) {
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
        System.out.println(m);
        return m;
    }

    public Piece selectPiece(Board board){
        if(board.getPieces().size() == 0) return null;
        BestMove m = null;
        for(Piece p : board.getPieces()){
            BestMove newBestMove = Heuristic.calculateBestMove(board.copy(), p);
            if(m == null)
                m = newBestMove;
            else if(newBestMove.getWeight() < m.getWeight())
                m = newBestMove;
        }
        System.out.println(m);
        return m.getPiece();
    }

    abstract double run(Node node, int depth);

    public String getName() {
        return name;
    }

}
