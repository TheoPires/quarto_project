package IA;

import model.Board;
import model.Piece;


public class Test {
    public Test () {
        Board board = new Board();
        board.setPiece(Piece.SMALL_SQUARE_HOLLOW_YELLOW, 0, 0);
        board.setPiece(Piece.SMALL_SQUARE_HOLLOW_BROWN, 1, 0);

        System.out.println(Heuristic.heuristicLVL4(board, 1,1, Piece.BIG_SQUARE_FIELD_BROWN));
    }

    public static void main(String[] args) {
        new Test();
    }

}
