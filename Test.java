public class Test {

    public static void main(String args[]) {
        Board board = new Board();

        board.setPiece(Piece.BIG_ROUND_FIELD_BROWN, 1, 1);
        board.setPiece(Piece.BIG_ROUND_HOLLOW_BROWN, 1, 3);

        System.out.println(board.getRow(1).toString());

        for (Couple couple : board.getEmptyCell()) {
            System.out.println("(" + couple.getFst()+","+ couple.getSnd()+")");
        }

        Heuristic h = new Heuristic(board);

        System.out.println(h.getOpenRowFromEmplacement(1, Piece.BIG_ROUND_FIELD_YELLOW));

    }
}
