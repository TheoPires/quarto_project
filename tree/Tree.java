package tree;

import IA.Minimax;
import model.Board;
import model.Couple;
import model.Piece;

import java.util.ArrayList;

public class Tree {

    private Node rootNode;

    public Tree () throws CloneNotSupportedException {

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
        board.setPiece(Piece.BIG_ROUND_HOLLOW_BROWN, 3, 2);
        board.setPiece(Piece.BIG_ROUND_FIELD_YELLOW, 0, 3);

        //Noeud root
        rootNode = new Node(1, board);

        System.out.println("minimax : " + new Minimax().minimax(1, rootNode));
    }

    public Node getNode(){
        return rootNode;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        new Tree();
    }
}
