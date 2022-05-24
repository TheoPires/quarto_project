package tree;

import IA.*;
import model.Board;
import model.Piece;


public class Tree {

    public Tree (){

        Board board = new Board();
        board.setPiece(Piece.SMALL_SQUARE_HOLLOW_YELLOW, 0, 1);
        board.setPiece(Piece.SMALL_SQUARE_HOLLOW_BROWN, 1, 0);
        board.setPiece(Piece.SMALL_SQUARE_FIELD_YELLOW, 2, 2);
        board.setPiece(Piece.SMALL_SQUARE_FIELD_BROWN, 3, 1);

        board.setPiece(Piece.SMALL_ROUND_HOLLOW_YELLOW, 0, 0);
        board.setPiece(Piece.SMALL_ROUND_HOLLOW_BROWN, 1, 3);
        board.setPiece(Piece.SMALL_ROUND_FIELD_YELLOW, 3, 0);
        board.setPiece(Piece.SMALL_ROUND_FIELD_BROWN, 1, 1);

        board.setPiece(Piece.BIG_SQUARE_HOLLOW_YELLOW, 0, 2);
        board.setPiece(Piece.BIG_SQUARE_HOLLOW_BROWN, 3, 3);
        board.setPiece(Piece.BIG_SQUARE_FIELD_YELLOW, 2, 0);
        board.setPiece(Piece.BIG_SQUARE_FIELD_BROWN, 2, 1);

        board.setPiece(Piece.BIG_ROUND_HOLLOW_YELLOW, 2, 3);
//        board.setPiece(Piece.BIG_ROUND_HOLLOW_BROWN, 3, 2);
//        board.setPiece(Piece.BIG_ROUND_FIELD_YELLOW, 1, 2);

        int depth = 3;
        int level  = 4;

        Node rootNode1 = new Node(1, null, board, depth);
        Node rootNode2 = new Node(-1, null, board.copy(),depth);
        System.out.println("Minimax(MAX) : " + new Minimax(1,"Minimax(MAX)").run(rootNode1, depth, level));
        System.out.println("Minimax(MIN) : " + new Minimax(-1,"Minimax(MIN)").run(rootNode2, depth, level));

        rootNode1 = new Node(1, null, board, depth);
        rootNode2 = new Node(-1, null, board.copy(), depth);
        System.out.println("Negamax(MAX) : " + new Negamax(1,"Negamax(MAX)").run(rootNode1, depth, level));
        System.out.println("Negamax(MIN) : " + new Negamax(-1,"Negamax(MIN)").run(rootNode2, depth, level));

        rootNode1 = new Node(1, null, board, depth);
        rootNode2 = new Node(-1, null, board.copy(), depth);
        System.out.println("AlphaBeta(MAX) : " + new Alphabeta(1,"AlphaBeta(MAX)").run(rootNode1, depth, level));
        System.out.println("AlphaBeta(MIN) : " + new Alphabeta(-1,"AlphaBeta(MIN)").run(rootNode2, depth, level));

        rootNode1 = new Node(1, null, board, depth);
        rootNode2 = new Node(-1, null, board.copy(), depth);
        System.out.println("NegAlphaBeta(MAX) : " + new NegAlphaBeta(1,"NegAlphaBeta(MAX)").run(rootNode1, depth, level));
        System.out.println("NegAlphaBeta(MIN) : " + new NegAlphaBeta(-1,"NegAlphaBeta(MIN)").run(rootNode2, depth, level));

        rootNode1 = new Node(1, null, board, depth);
        rootNode2 = new Node(-1, null, board.copy(), depth);
        System.out.println("SSS*(MAX) : " + new SSSstar(1,"SSS*(MAX)").run(rootNode1, depth, level));
        System.out.println("SSS*(MIN) : " + new SSSstar(-1,"SSS*(MIN)").run(rootNode2, depth, level));


//        System.out.println("Test priorityQueue :");
//        SSSNode testNode = new SSSNode(rootNode1, 1);
//        System.out.println("*** Ordre d\'insertion ***");
//        System.out.println(new SSSstar().testPriorityQ(testNode));
//        System.out.println("Résultat attendu :");
//        System.out.println("[(18, v, Infinity), (18, v, 97.0), (18, r, 23.0), (18, v, 23.0), (18, r, 23.0), (18, v, 22.0)]");
    }

    public static void main(String[] args) {
        new Tree();
    }
}
