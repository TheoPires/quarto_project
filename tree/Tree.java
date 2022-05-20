package tree;

import IA.Alphabeta;
import IA.Heuristic;
import IA.Minimax;
import IA.Negamax;
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

//        Noeud root
        rootNode = new Node(-1, null, board);

//        System.out.println("minimax : " + new Minimax().minimax(5, rootNode));
//        System.out.println("negamax : " + new Negamax().negamax(5, rootNode));
//        System.out.println("alphaBeta : " + new Alphabeta().alphaBeta(rootNode,Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,3));
    }

    public Node getNode(){
        return rootNode;
    }

    public static void main(String[] args) {
        new Tree();
    }
}
