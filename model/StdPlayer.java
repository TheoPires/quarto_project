package model;

public class StdPlayer implements Player{
    protected String name;

    public StdPlayer(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
