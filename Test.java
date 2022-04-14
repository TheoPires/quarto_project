import IA.Heuristic;
import model.Board;
import model.Move;
import model.Piece;

public class Test {

    public static void main(String args[]) {
        Board board = new Board();

        board.setPiece(Piece.BIG_ROUND_FIELD_BROWN, 1, 1);
        board.setPiece(Piece.BIG_ROUND_HOLLOW_BROWN, 1, 3);

        board.setPiece(Piece.BIG_ROUND_FIELD_YELLOW, 0,  0);
        board.setPiece(Piece.SMALL_SQUARE_FIELD_YELLOW, 3,0);

        board.setPiece(Piece.SMALL_SQUARE_HOLLOW_BROWN, 0, 3);

        System.out.println(board.getRow(1).toString());

        for (Move move : board.getEmptyCell()) {
            System.out.println("(" + move.getX()+","+ move.getY()+")");
        }

        Heuristic h = new Heuristic(board);

        System.out.println("Valeur getOpenRowFromEmplacement :");
        System.out.println(h.getOpenRowFromEmplacement(1, Piece.BIG_ROUND_FIELD_YELLOW));

        System.out.println("Valeur getOpenColumnFromEmplacement :");
        System.out.println(h.getOpenColumnFromEmplacement(0, Piece.BIG_ROUND_FIELD_YELLOW));

        System.out.println("Valeur getOpenDiagonalLeftToRight :");
        System.out.println(h.getOpenDiagonalLeftToRight(1, 2, Piece.BIG_ROUND_HOLLOW_BROWN));

        System.out.println("Valeur getOpenDiagonalRightToLeft :");
        System.out.println(h.getOpenDiagonalRightToLeft(1, 2, Piece.BIG_ROUND_HOLLOW_BROWN));

        System.out.println("BestMove :");
        Move m = h.calculateBestMove(Piece.SMALL_ROUND_HOLLOW_BROWN);
        System.out.println(m.toString());



    }
}
