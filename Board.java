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
        if (x < SIZE) {
            throw new AssertionError();
        }
        List<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < SIZE; i++) {
            Piece p = getPiece(x,i);
            if (p != null) {
                result.add(p);
            }
        }
        return result;
    }
    public List<Piece> getColumn(int y) {
        if (y < SIZE) {
            throw new AssertionError();
        }
        List<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < SIZE; i++) {
            Piece p = getPiece(i,y);
            if (p != null) {
                result.add(p);
            }
        }
        return result;

    }

    /* A REFAIRE POUR LES DIAGONALES*/
    public List<Piece> getDiagonal(int x, int y) {
        List<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < SIZE; i++) {
            Piece p = getPiece(x + i, y + i);
            if (p != null) {
                result.add(p);
            }
        }
        return result;
    }

    // Commandes
    public void setPiece(Piece piece, int x, int y) {
        board[x][y] = piece;
    }





}
