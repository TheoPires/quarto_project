package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // Constante
    final int SIZE = 4;

    // Attribut
    private Piece[][] board;

    // Constructeur
    public Board() {
        board = new Piece[SIZE][SIZE];
    }

    // Requêtes
    public int getSIZE() {
        return SIZE;
    }

    public Piece getPiece(int x, int y) {
        if (x >= SIZE || y >= SIZE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return board[x][y];
    }

    public List<Piece> getRow(int x) {
        if (x >= SIZE || x < 0) {
            throw new AssertionError();
        }
        List<Piece> result = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Piece p = getPiece(x,i);
            result.add(p);
        }
        return result;
    }

    public List<Piece> getColumn(int y) {
        if (y >= SIZE || y < 0) {
            throw new AssertionError();
        }
        List<Piece> result = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Piece p = getPiece(i,y);
            result.add(p);
        }
        return result;

    }

    public List<Piece> getDiagonal(int x, int y) {
        List<Piece> result = new ArrayList<>();
        if(x == y) {
            for (int i = 0; i < getSIZE(); i++) {
                result.add(board[i][i]);
            }
        }else if(x+y == 3){
            for(int i=3,j=0; i>=0; i--,j++){
                System.out.println(i+" "+j);
                result.add(board[i][j]);
            }
        }
        return result;
    }

    public List<Move> getEmptyCell() {
        ArrayList<Move> list = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board[row][column] == null) {
                    list.add(new Move(row, column, null));
                }
            }
        }
        return list;
    }

    // Commandes
    public void setPiece(Piece piece, int row, int column) {
        board[row][column] = piece;
    }





}
