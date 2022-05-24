package IA;

import model.*;

import java.util.*;

public class Heuristic {

    public static BestMove calculateBestMove(Board board, Piece piece, int level) {
        List<Couple> listMove = board.getEmptyCell();
        List<Move> listBestMove = new ArrayList<>();
        int bestWeight = 0;
        for (Couple c : listMove) {
            int moveWeight = calulateWeight(board, c,piece, level);
            if (moveWeight > bestWeight) {
               listBestMove.clear();
               listBestMove.add(new Move(c.getX(),c.getY(),piece));
                bestWeight = moveWeight;
            }else if(moveWeight == bestWeight){
                listBestMove.add(new Move(c.getX(),c.getY(),piece));
            }
        }
        Random r = new Random();
        Move m = listBestMove.get(r.nextInt(listBestMove.size()));
        return new BestMove(m,bestWeight);
    }

    public static int calulateWeight(Board board, Couple couple, Piece piece, int level) {
        int result = 0;
        switch (level){
            case 1 -> result += heuristicLVL1(board, couple.getX(), couple.getY(),piece);
            case 2 -> {
                result += heuristicLVL1(board, couple.getX(), couple.getY(),piece);
                result += heuristicLVL2(board, couple.getX(), couple.getY(),piece);
            }
            case 3 -> {
                result += heuristicLVL1(board, couple.getX(), couple.getY(),piece);
                result += heuristicLVL2(board, couple.getX(), couple.getY(),piece);
                result += heuristicLVL3(board, couple.getX(), couple.getY(),piece);
            }
            case 4 -> {
                result += heuristicLVL1(board, couple.getX(), couple.getY(),piece);
                result += heuristicLVL2(board, couple.getX(), couple.getY(),piece);
                result += heuristicLVL3(board, couple.getX(), couple.getY(),piece);
                result += heuristicLVL4(board, couple.getX(), couple.getY(),piece);
            }
            default -> {
            }
        }
        return result;
    }

    public static int getOpenRowFromEmplacement(Board board, int row, Piece testedPiece) {
        int result = 0;
        for (int column = 0; column < board.getSIZE(); column++) {
            Piece piece = board.getPiece(row, column);
            result += compareTwoPiece(piece, testedPiece);
        }
        return result;
    }

    public static int getOpenColumnFromEmplacement(Board board, int column, Piece testedPiece) {
        int result = 0;
        for (int row = 0; row < board.getSIZE(); row++) {
            Piece piece = board.getPiece(row, column);
            result += compareTwoPiece(piece, testedPiece);
        }
        return result;
    }

    public static int getOpenDiagonalLeftToRight(Board board, int row, int column, Piece testedPiece) {
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

    public static int getOpenDiagonalRightToLeft(Board board, int row, int column, Piece testedPiece) {
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

    public static int heuristicLVL1(Board board, int row, int column, Piece piece){
        int result = 0;
        result += getOpenRowFromEmplacement(board, row, piece);
        result += getOpenColumnFromEmplacement(board, column, piece);
        result += getOpenDiagonalLeftToRight(board, row, column, piece);
        result += getOpenDiagonalRightToLeft(board, row, column, piece);
        return result;
    }

    public static int heuristicLVL2(Board board, int row, int column, Piece testedPiece) {
        List<Couple> allPossibilities = new ArrayList<>();
        allPossibilities.add(new Couple(0,0));
        allPossibilities.add(new Couple(0,1));
        allPossibilities.add(new Couple(1,0));
        allPossibilities.add(new Couple(1,1));
        allPossibilities.add(new Couple(0,-1));
        allPossibilities.add(new Couple(-1,0));
        allPossibilities.add(new Couple(-1,-1));
        allPossibilities.add(new Couple(-1,1));
        allPossibilities.add(new Couple(1,-1));
        return calculHeuristic(board,row,column,testedPiece,allPossibilities);
    }

    public static int heuristicLVL3(Board board, int row, int column, Piece testedPiece) {
        List<Couple> allPossibilities = new ArrayList<>();
        allPossibilities.add(new Couple(0,0));
        allPossibilities.add(new Couple(0,2));
        allPossibilities.add(new Couple(2,0));
        allPossibilities.add(new Couple(2,2));
        allPossibilities.add(new Couple(0,-2));
        allPossibilities.add(new Couple(-2,0));
        allPossibilities.add(new Couple(-2,-2));
        allPossibilities.add(new Couple(-2,2));
        allPossibilities.add(new Couple(2,-2));
        return calculHeuristic(board,row,column,testedPiece,allPossibilities);
    }

    public static int heuristicLVL4(Board board, int row, int column, Piece testedPiece) {
        if (row > 3 || column > 3) {
            return 0;
        }
        List<Couple> allPossibilities = new ArrayList<>();
        allPossibilities.add(new Couple(-1,-1));
        allPossibilities.add(new Couple(1,1));
        allPossibilities.add(new Couple(-1,1));
        allPossibilities.add(new Couple(1,-1));
        return calculHeuristic(board,row,column,testedPiece,allPossibilities);
    }

    public static int calculHeuristic(Board board, int row, int column, Piece testedPiece, List<Couple> allPossibilities) {
        if (row > 3 || column > 3) {
            return 0;
        }
        ArrayList<Couple> remove = new ArrayList<>();
        allPossibilities.removeIf(couple -> {
            if(!isLegal(board, row + couple.getX(), column + couple.getY())){
                remove.add(couple);
                return true;
            }
            return false;
        });
        allPossibilities.removeAll(remove);

        int result = 0;
        for (Couple c : allPossibilities) {
            Piece p = board.getPiece(row + c.getX(), column + c.getY());
            if (p == null) {
                result += 1;
            } else {
                result += compareTwoPiece(p, testedPiece);
            }
        }
        return result;
    }

    private static boolean isLegal(Board b, int row, int column) {
        return 0 <= row && row < b.getSIZE() && 0 <= column && column < b.getSIZE();
    }

    private static int compareTwoPiece(Piece firstPiece, Piece secondPiece) {
        int result = 0;
        if (firstPiece != null && firstPiece != secondPiece) {
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
            //Case Vide
            result += 1;
        }
        return result;
    }
}
