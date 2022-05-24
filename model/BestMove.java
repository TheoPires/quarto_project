package model;

public class BestMove extends Move{

    private double weight;

    public BestMove(int x, int y, Piece piece, double weight) {
        super(x, y, piece);
        this.weight = weight;
    }
    public BestMove(Move m, double weight) {
        super(m.getX(), m.getY(), m.getPiece());
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
