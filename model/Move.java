package model;

public class Move {
    // ATTRIBUTES
    private int x;
    private int y;
    private Piece piece;

    // CONSTRUCTOR
    public Move(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    // REQUESTS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }


    //METHODS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String toString() {
        return "[" + x + ", " + y + "] : " + ((piece == null ) ? "Null" : piece.name());
    }
}