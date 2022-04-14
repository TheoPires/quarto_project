package IA;

import model.Board;
import model.Move;
import model.Piece;

import java.util.*;

public class Heuristic {

    private Board board;

    public  Heuristic (Board board){
        this.board = board;
    }
    public Heuristic(){
        this.board = new Board();
    }

    public Move calculateBestMove(Piece piece) {
        List<Move> listMove = board.getEmptyCell();
        List<Move> listBestMove = new ArrayList<>();
        Move bestMove = null;
        int bestWeight = 0;
        for (Move m : listMove) {
            int moveWeight = 0;
            moveWeight += getOpenRowFromEmplacement(m.getX(), piece);
            moveWeight += getOpenColumnFromEmplacement(m.getY(), piece);
            moveWeight += getOpenDiagonalLeftToRight(m.getX(), m.getY(), piece);
            moveWeight += getOpenDiagonalRightToLeft(m.getX(), m.getY(), piece);
            if (moveWeight > bestWeight) {
               listBestMove.clear();
               listBestMove.add(m);
                bestWeight = moveWeight;
                bestMove = m;
            }else if(moveWeight == bestWeight){
                listBestMove.add(m);
            }
        }
        Random r = new Random();
        return listBestMove.get(r.nextInt(listBestMove.size()));
    }

    public int getOpenRowFromEmplacement(int row, Piece testedPiece) {
        int result = 0;
        for (int column = 0; column < board.getSIZE(); column++) {
            Piece piece = board.getPiece(row, column);
            result += compareTwoPiece(piece, testedPiece);
        }
        return result;
    }

    public int getOpenColumnFromEmplacement(int column, Piece testedPiece) {
        int result = 0;
        for (int row = 0; row < board.getSIZE(); row++) {
            Piece piece = board.getPiece(row, column);
            result += compareTwoPiece(piece, testedPiece);
        }
        return result;
    }

    public int getOpenDiagonalLeftToRight(int row, int column, Piece testedPiece) {
        if (row != column) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < board.getSIZE(); i++) {
            Piece piece = board.getPiece(i, i);
            result += compareTwoPiece(piece, testedPiece);
        }
        return result;
    }

    public int getOpenDiagonalRightToLeft(int row, int column, Piece testedPiece) {
        if ((row + column) != 3) {
            return 0;
        }
        int result = 0;
        for (int i = 0, j = 3; i < board.getSIZE(); i++, j--) {
            Piece piece = board.getPiece(i, j);
            result += compareTwoPiece(piece, testedPiece);
        }
        return result;
    }

    private int compareTwoPiece(Piece firstPiece, Piece secondPiece) {
        int result = 0;
        if (firstPiece != null) {
            if (firstPiece.isBig() == secondPiece.isBig()) {
                result += 1;
            }
            if (firstPiece.getForm().equals(secondPiece.getForm())) {
                result += 1;
            }
            if (firstPiece.isHollow() == secondPiece.isHollow()) {
                result += 1;
            }
            if (firstPiece.getColor().equals(secondPiece.getColor())) {
                result += 1;
            }
        } else {
            result += 1;
        }
        return result;
    }
}
