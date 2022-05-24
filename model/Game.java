package model;

import IA.Algorithm;
import controller.QuartoController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    //Attributs
    private QuartoController controller;
    private Board board;
    private List<Move> movesPlay;
    private Piece selectedPiece;
    private Player player0;
    private Player player1;
    private Player currentPlayer;
    private int level;

    private boolean needSelectedPiece;
    private boolean needPlacePiece;

    private boolean isGameFinish = false;

    private int depth;

    public Game(QuartoController controller, int level){
        this.controller = controller;
        this.level = level;
        init();
    }

    //Requêtes
    public List<Piece> getPieces(){
        return this.board.getPieces();
    }
    public List<Move> getMoves(){
        return this.movesPlay;
    }

    public int getSize(){
        return this.board.getSIZE();
    }

    public void init(){
        movesPlay = new ArrayList<>();
        board = new Board();
        this.selectedPiece = null;
    }

    //Commandes
    public void play(Move move){
        controller.refresh();
        board.playMove(move);
        movesPlay.add(move);
        controller.addHistoryPlace(move.getX(), move.getY());
        controller.refresh();
        selectedPiece = null;
        needSelectedPiece = true;
        needPlacePiece = false;
        if(isGameFinish()){
            controller.endGame();
        }else {
            if(currentPlayer instanceof Algorithm) {
                /*try {
                    controller.refresh();
                    //Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                setSelectedPiece(((Algorithm)currentPlayer).selectPiece(board.copy(),level).getNameImage());
            }else if(currentPlayer != null)
                controller.setTxtSelectedPiece();
        }
    }

    public void startGame(){
        init();
        needSelectedPiece = true;
        needPlacePiece = false;
        currentPlayer = player0;
        if(currentPlayer instanceof Algorithm)
            setSelectedPiece(((Algorithm)currentPlayer).selectPiece(board.copy(), level).getNameImage());
        else if(currentPlayer != null)
            controller.setTxtSelectedPiece();
    }

    public void setSelectedPiece(String namePiece){
        controller.refresh();
        for(Piece p : this.board.getPieces()){
            if(p.getNameImage().equals(namePiece)){
                selectedPiece = p;
                controller.addHistorySelectedPiece(p.getNameImage());
                break;
            }
        }
        needSelectedPiece = false;
        needPlacePiece = true;
        //Switch player
        currentPlayer = (currentPlayer == player0)?player1:player0;
        controller.switchPlayer();
        controller.refresh();
        if(currentPlayer instanceof Algorithm)
            play(((Algorithm)currentPlayer).play(board.copy(), selectedPiece, level));
        else if(currentPlayer != null)
            controller.setTxtPlacePiece();

    }
    public void setPlayer(Player player,int numPlayer){
        player0 = (numPlayer == 0)?player:player0;
        player1 = (numPlayer == 1)?player:player1;
    }

    public boolean canSelectedNewPiece() {
        return needSelectedPiece;
    }
    public boolean canPlaceNewPiece() {
        return needPlacePiece;
    }

    public void setMove(int x, int y){
        play(new Move(x,y,selectedPiece));
    }

    public String getCurrentPlayerName(){
        return currentPlayer.getName();
    }
    public String getSelectedPieceName(){
        return (selectedPiece != null)?selectedPiece.getNameImage():null;
    }
    public boolean isGameFinish(){
        return switch (level) {
            case 1 -> isGameNull()
                    || isGameFinishLevel1();
            case 2 -> isGameNull()
                    || isGameFinishLevel1()
                    || isGameFinishLevel2();
            case 3 -> isGameNull()
                    || isGameFinishLevel1()
                    || isGameFinishLevel2()
                    || isGameFinishLevel3();
            case 4 -> isGameNull()
                    || isGameFinishLevel1()
                    || isGameFinishLevel2()
                    || isGameFinishLevel3()
                    || isGameFinishLevel4();
            default -> isGameNull();
        };
    }


    private boolean isGameNull(){
        //true if game isn't finish and not empty cells
        return !isGameFinish && board.getEmptyCell().size() == 0;
    }

    private boolean isGameFinishLevel1(){
        for(int i = 0; i< getSize();i++){
            if(haveCaracteristicInCommun(board.getRow(i)))return true;
            if(haveCaracteristicInCommun(board.getColumn(i)))return true;
        }
        if(haveCaracteristicInCommun(board.getDiagonal(0,0))) return true;
        if(haveCaracteristicInCommun(board.getDiagonal(3,0))) return true;

        return false;
    }
    private boolean isGameFinishLevel2(){
        for(int i = 0; i < board.getSIZE()-1; i++){
            for(int j = 0; j < board.getSIZE()-1; j++){
                List<Piece> testPiece = new ArrayList<>();
                testPiece.add(board.getPiece(i,j));
                testPiece.add(board.getPiece(i, j+1));
                testPiece.add(board.getPiece(i+1,j));
                testPiece.add(board.getPiece(i+1,j+1));
                if(haveCaracteristicInCommun(testPiece)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isGameFinishLevel3(){
        for(int i = 0; i < board.getSIZE()-2; i++){
            for(int j = 0; j < board.getSIZE()-2; j++){
                List<Piece> testPiece = new ArrayList<>();
                testPiece.add(board.getPiece(i,j));
                testPiece.add(board.getPiece(i, j+2));
                testPiece.add(board.getPiece(i+2,j));
                testPiece.add(board.getPiece(i+2,j+2));
                if(haveCaracteristicInCommun(testPiece)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isGameFinishLevel4(){
        boolean isFinished = false;
        List<Piece> testPiece= new ArrayList<>();
        testPiece.add(board.getPiece(1, 0));//a2
        testPiece.add(board.getPiece(0, 1));//b1
        testPiece.add(board.getPiece(1, 2));//c2
        testPiece.add(board.getPiece(2, 1));//b3

        isFinished = isFinished || haveCaracteristicInCommun(testPiece);

        testPiece = new ArrayList<>();
        testPiece.add(board.getPiece(2, 1));//b3
        testPiece.add(board.getPiece(1,2));//c2
        testPiece.add(board.getPiece(2, 3));//d3
        testPiece.add(board.getPiece(3, 2));//c4

        isFinished = isFinished || haveCaracteristicInCommun(testPiece);

        testPiece= new ArrayList<>();
        testPiece.add(board.getPiece(2, 0)); //a3
        testPiece.add(board.getPiece(1, 1)); //b2
        testPiece.add(board.getPiece(3, 1)); //b4
        testPiece.add(board.getPiece(2, 2)); //c3

        isFinished = isFinished || haveCaracteristicInCommun(testPiece);

        testPiece= new ArrayList<>();
        testPiece.add(board.getPiece(1, 1));//b2
        testPiece.add(board.getPiece(0, 2));//c1
        testPiece.add(board.getPiece(1, 3));//d2
        testPiece.add(board.getPiece(2, 2));//c3

        isFinished = isFinished || haveCaracteristicInCommun(testPiece);

        return isFinished;
    }


    private boolean haveCaracteristicInCommun(List<Piece> list){
        // [0]BIG,     [1] SMALL, [2] SQUARE, [3] ROUND
        // [4] HOLLOW, [5] FIELD, [6] YELLOW, [7] BROWN

        int[] temp = new int[8];  //Stock all commun caracteristics
        for(Piece p : list){
            if(p != null){
                if(p.isBig()){temp[0]++;}else{temp[1]++;}
                if(p.getForm().equals("SQUARE")){temp[2]++;}else{temp[3]++;}
                if(p.isHollow()){temp[4]++;}else{temp[5]++;}
                if(p.getColor().equals(Color.YELLOW)){temp[6]++;}else{temp[7]++;}
            }
        }
            for(int i = 0; i<temp.length;i++){
                if(temp[i] == 4) return true;
            }
        return false;
    }
}
