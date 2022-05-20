package model;

import controller.QuartoController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    //Attributs
    private QuartoController controller;
    private Board board;
    private List<Move> movesPlay;
    private Piece selectedPiece;

    private boolean needSelectedPiece;
    private boolean needPlacePiece;

    private boolean isGameFinish = false;

    private int depth;

    public Game(QuartoController controller){
        this.controller = controller;
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
        System.out.println(move.getPiece()+" "+move.getX()+" "+move.getY());
        board.playMove(move);
        movesPlay.add(move);
        selectedPiece = null;
        needSelectedPiece = true;
        needPlacePiece = false;

        if(isFinishedLevel1()){
            controller.endGame();
        }
    }

    public void startGame(){
        init();
        needSelectedPiece = true;
        needPlacePiece = false;

    }

    public void setSelectedPiece(String namePiece){
        for(Piece p : this.board.getPieces()){
            if(p.getNameImage().equals(namePiece)){
                selectedPiece = p;
            }
        }
        needSelectedPiece = false;
        needPlacePiece = true;
    }

    public boolean canSelectedNewPiece() {
        return needSelectedPiece;
    }
    public boolean canPlaceNewPiece() {
        return needPlacePiece;
    }

    public void setPiece(int x, int y){
        play(new Move(x,y,selectedPiece));
    }

    public boolean isGameFinish(){
        return isGameNull()
                || isFinishedLevel1()
                /*||  isFinishedLevel2
                * ||  isFinishedLevel3
                * ||  isFinishedLevel4
                * */;
    }

    private boolean isGameNull(){
        //true if game isn't finish and not empty cells
        return !isGameFinish && board.getEmptyCell().size() == 0;
    }

    private boolean isFinishedLevel1(){
        for(int i = 0; i< getSize();i++){
            if(haveCaracteristicInCommun(board.getRow(i))){
                return true;
            }
            if(haveCaracteristicInCommun(board.getColumn(i))){
                return true;
            }
        }

        if(haveCaracteristicInCommun(board.getDiagonal(0,0))) return true;
        if(haveCaracteristicInCommun(board.getDiagonal(3,0))) return true;

        return false;
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
                System.out.print(temp[i]+", ");
                if(temp[i] == 4) return true;
            }
        System.out.println("\n---------------");
        return false;
    }
    public void newGame(){
        init();
    }
}
