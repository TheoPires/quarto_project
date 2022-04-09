public class Couple {

    private Integer x;
    private Integer y;

    public Couple(Integer fst, Integer snd) {
        super();
        this.x = fst;
        this.y = snd;
    }

    public Integer getFst() {
        return x;
    }

    public void setFst(String key) {
        this.x = x;
    }

    public Integer getSnd() {
        return y;
    }

    public void setSnd(String value) {
        this.y = y;
    }

    public Object[] getCouple() {
        return new Object[] { this.x, this.y};
    }
}
