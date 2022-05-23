package model;

public class Couple {

    private Integer x;
    private Integer y;

    public Couple(Integer fst, Integer snd) {
        this.x = fst;
        this.y = snd;
    }

    public Integer getX() {
        return x;
    }

    public void setX(String key) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(String value) {
        this.y = y;
    }

    public Object[] getCouple() {
        return new Object[] { this.x, this.y};
    }

    @Override
    public String toString() {
        return "Couple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
