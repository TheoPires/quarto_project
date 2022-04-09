import java.util.ArrayList;
import java.util.List;

public class Heuristic {

    private Board board;
    private Heuristic (Board board){
        this.board = board;
    }
    public Heuristic(){
        this.board = new Board();
    }

    public Move calculateBestMove(Board board){

        /*for(Piece p: board.getLastPiece()){
            getOpenRowFromEmplacement()
            getOpenColumnFromEmplacement()
            getOpenDiagonalFromEmplacement()
        }*/
        return null;
    }

    //x:row y:Column
    public int getOpenRowFromEmplacement(int row, Piece testedPiece) {
        int result = 0;
        for (int column = 0; column < board.getSIZE(); column++) {
            Piece piece = board.getPiece(row, column);
            if (piece != null) {
                System.out.println("ICI");
                if (piece.isBig() == testedPiece.isBig()) {
                    result += 1;
                }
                if (piece.isHollow() && testedPiece.isHollow()) {
                    result += 1;
                }
                if (piece.getColor().equals(testedPiece.getColor())) {
                    result += 1;
                }
                if (piece.getForm().equals(testedPiece.getForm())) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private int getOpenColumnFromEmplacement(int x, int y,Piece testedPiece) {

        return 0;
    }
    private int getOpenDiagonalFromEmplacement(int x, int y, Piece testedPiece) {

        return 0;
    }

    private boolean isOpenRaw(int x,Piece testedPiece) {
        List<Piece> pieces = board.getRow(x);
        List<Piece> sameCarateristicsPiece = new ArrayList<>();
        if (pieces.size() == 0) return true;

        for (Piece p : pieces) {
            if (p.haveOneCaracteristicsInCommun(testedPiece)) {
                for (Piece piece : sameCarateristicsPiece) {
                    if (!p.haveOneCaracteristicsInCommun(piece)) {
                        return false;
                    }
                    sameCarateristicsPiece.add(piece);
                }
            }
        }
        return false;
    }
}
