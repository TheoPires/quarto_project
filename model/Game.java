package model;

import controller.QuartoController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    //Attributs
    private QuartoController controller;
    private Board board;
    private List<Piece> pieces;
    private int level;

    public Game(QuartoController controller){
        this.controller = controller;
        board = new Board();
        init();
    }

    //Requêtes
    public List<Piece> getPieces(){
        return this.pieces;
    }

    public int getSize(){
        return this.board.getSIZE();
    }

    public void init(){
        pieces = new ArrayList<>();
        pieces.add(Piece.SMALL_SQUARE_HOLLOW_YELLOW);
        pieces.add(Piece.SMALL_SQUARE_HOLLOW_BROWN);
        pieces.add(Piece.SMALL_SQUARE_FIELD_YELLOW);
        pieces.add(Piece.SMALL_SQUARE_FIELD_BROWN);

        pieces.add(Piece.SMALL_ROUND_HOLLOW_YELLOW);
        pieces.add(Piece.SMALL_ROUND_HOLLOW_BROWN);
        pieces.add(Piece.SMALL_ROUND_FIELD_YELLOW);
        pieces.add(Piece.SMALL_ROUND_FIELD_BROWN);

        pieces.add(Piece.BIG_SQUARE_HOLLOW_YELLOW);
        pieces.add(Piece.BIG_SQUARE_HOLLOW_BROWN);
        pieces.add(Piece. BIG_SQUARE_FIELD_YELLOW);
        pieces.add(Piece.BIG_SQUARE_FIELD_BROWN);

        pieces.add(Piece.BIG_ROUND_HOLLOW_YELLOW);
        pieces.add(Piece.BIG_ROUND_HOLLOW_BROWN);
        pieces.add(Piece.BIG_ROUND_FIELD_YELLOW);
        pieces.add(Piece.BIG_ROUND_FIELD_BROWN);
    }
    //Commandes
    public void play(){

    }

    public boolean isGameFinishLevel1() {
        List<List<Piece>> listLine = new ArrayList<List<Piece>>();
        for (int i = 0; i < board.getSIZE(); i++){

            if (board.getRow(i).size() == board.getSIZE()){
                //Traitement
                /* pour chaque caractéristique de la premeiere pièce faire*/
                    //la comparaison de la caractéristique avec les 4 caractéristiques des autres pièces
                    //Si les pièces ont la même caractéristique alors
                        //return true
                //fin pour

            }
            if(board.getColumn(i).size() == board.getSIZE()){
                //Traitement
            }
        }
        if(board.getDiagonal(0,0).size() == board.getSIZE()){

        }
        return false;
    }
}
