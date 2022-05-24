package controller;

import model.Game;
import model.Move;
import model.Piece;
import model.Player;
import view.QuartoView;

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
    public QuartoController() {newGame();}

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
        refresh();


    }

    public void newGame(){
        int level = QuartoView.initLevel();
        this.game = new Game(this, level);
        this.view = new QuartoView(this, level);
        this.isGameStarted = false;
    }
    public void setSelectedPieceSelectedPlace(int x, int y){
        view.setTxtSelectPiece();
        game.setMove(x,y);
        refresh();
    }
    public void setTxtPlacePiece(){
        view.setTxtPlacePiece();
        refresh();
    }
    public void setTxtSelectedPiece(){
        view.setTxtSelectPiece();
        refresh();
    }
    public void setPlayer(Player player,int numPlayer){
        game.setPlayer(player, numPlayer);
    }

    public void endGame(){
        view.endGame();
    }

    public String getSelectedPieceName(){
        return game.getSelectedPieceName();
    }

    public String getCurrentPlayerName(){
        return game.getCurrentPlayerName();
    }

    public void addHistoryPlace(int x, int y){
        view.addHistoryPlace(x,y);
    }
    public void addHistorySelectedPiece(String namePiece){
        view.addHistorySelectedPiece(namePiece);
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

    public void switchPlayer() {
        view.switchPlayer();
    }
}

