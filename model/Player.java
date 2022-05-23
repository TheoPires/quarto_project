package model;

public abstract class Player {
    protected int who;

    protected abstract Move play(Board board, Piece selectedPiece);

    public int getWho(){
        return this.who;
    }
}
