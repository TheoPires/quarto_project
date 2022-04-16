package controller;

import model.Game;
import model.Move;
import model.Piece;
import view.QuartoView;
import view.SquareButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuartoController
{

    private Game game;
    private QuartoView view;

    private boolean isGameStarted;


    public static void main(String[] args)
    {
        QuartoController qc = new QuartoController();
    }

    /**
     * Initializes all the ActionListeners needed by the ChessGUI to run as a game.
     */
    public QuartoController()
    {
        this.game = new Game(this);
        this.view = new QuartoView(this);
        this.isGameStarted = false;

    }

    public int getSizes() {
        return game.getSize();
    }

    public List<Piece> getPieces(){
        return List.copyOf(game.getPieces());
    }
    public List<Move> getMoves(){
        return List.copyOf(game.getMoves());
    }
    public void startGame(){
        game.startGame();
        isGameStarted = true;
    }

    public void setSelectedPiece(String namePiece){
        game.setSelectedPiece(namePiece);
        view.updateSelectedPiece(namePiece);
        view.setTxtPlacePiece();
        refresh();


    }


    public void setSelectedPieceSelectedPlace(int x, int y){
        game.setPiece(x,y);
        view.setTxtSelectPiece();
        refresh();
    }

    public boolean canSelectedNewPiece(){
        return game.canSelectedNewPiece();
    }
    public boolean canPlaceNewPiece(){
        return game.canPlaceNewPiece();
    }

    public boolean isGameStarted(){
        return isGameStarted;
    }

    public void refresh(){
        view.refresh();
    }
}
