package tree;

import IA.*;
import model.Board;
import model.Couple;
import model.Piece;

import java.awt.*;
import java.util.ArrayList;

public class Tree {

    private Node rootNode;

    public Tree (){

        Board board = new Board();
        board.setPiece(Piece.SMALL_SQUARE_HOLLOW_YELLOW, 0, 1);
//        Board firstCopy = board.copy();
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
        board.setPiece(Piece.BIG_ROUND_HOLLOW_BROWN, 3, 2);

        Node rootNode1 = new Node(1, null, board);
        Node rootNode2 = new Node(-1, null, board.copy());
        System.out.println("Minimax(MAX) : " + new Minimax().run(rootNode1, 5));
        System.out.println("Minimax(MIN) : " + new Minimax().run(rootNode2, 5));

        rootNode1 = new Node(1, null, board);
        rootNode2 = new Node(-1, null, board.copy());
        System.out.println("Negamax(MAX) : " + new Negamax().run(rootNode1, 5));
        System.out.println("Negamax(MIN) : " + new Negamax().run(rootNode2, 5));

        rootNode1 = new Node(1, null, board);
        rootNode2 = new Node(-1, null, board.copy());
        System.out.println("AlphaBeta(MAX) : " + new Alphabeta().run(rootNode1, 5));
        System.out.println("AlphaBeta(MIN) : " + new Alphabeta().run(rootNode2, 5));

//        rootNode1 = new Node(1, null, board);
//        rootNode2 = new Node(-1, null, board.copy());
//        System.out.println("SSS*(MAX) : " + new SSSstar().run(rootNode1, 5));
//        System.out.println("SSS*(MIN) : " + new SSSstar().run(rootNode2, 5));
    }

    public Node getNode(){
        return rootNode;
    }

    public static void main(String[] args) {
        new Tree();
    }
}
