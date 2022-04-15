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

    }

    public int getSizes() {
        return game.getSize();
    }

    public List<Piece> getPiecesClone(){
        return List.copyOf(game.getPieces());
    }
}

