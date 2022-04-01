import java.util.ArrayList;
import java.util.List;

public class Heuristic {

    private Board board;
    private Heuristic (Board board){
        this.board = board;
    }
    private Heuristic(){
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
    private int getOpenRowFromEmplacement(int x, int y, Piece testedPiece) {
        for(int i = 0; i< board.getSIZE(); i++) {
        }
        return 0;
    }

    private int getOpenColumnFromEmplacement(int x, int y,Piece testedPiece) {

        return 0;
    }
    private int getOpenDiagonalFromEmplacement(int x, int y, Piece testedPiece) {

        return 0;
    }

    private boolean isOpenRaw(int x,Piece testedPiece){
        List<Piece> pieces = board.getRow(x);
        List<Piece> sameCarateristicsPiece = new ArrayList<>();
        if(pieces.size() == 0) return true;

        for(Piece p : pieces) {
            if (p.haveOneCaracteristicsInCommun(testedPiece)) {

            }
        }
        return false;
    }
}
