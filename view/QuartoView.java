package view;

import IA.Algorithm;
import IA.Alphabeta;
import IA.Minimax;
import IA.Negamax;
import controller.QuartoController;
import model.Player;
import model.StdPlayer;

import javax.swing.*;
import java.awt.*;

public class QuartoView {

    private QuartoController controller;

    private JFrame mainFrame;
    private JPanel playersPanel;
    private GameMsgPanel gameMsgPanel;
    private BoardPanel boardPanel;
    private InfoPanel infoPanel;
    private PieceListPanel piecePanel;
    private int level;

    private PlayerLabel[] players = new PlayerLabel[2];
    private PlayerLabel currentPlayer;

    /**
     * Initializes all the components of the GUI and puts them into a JFrame to display.
     */
    public QuartoView(QuartoController controller, int level)
    {
        this.controller = controller;
        this.level = level;
        this.mainFrame = new JFrame("Quarto !");

        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);

        setupPlayerPanel();
        mainFrame.add(this.playersPanel,BorderLayout.SOUTH);
        init();
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    public void init(){
        setupBoardPanel();
        setupInfoPanel();
        setupFreePieceList();
        setupGameTxt();
        mainFrame.add(this.boardPanel,BorderLayout.CENTER);
        mainFrame.add(this.infoPanel,BorderLayout.EAST);
        mainFrame.add(this.piecePanel,BorderLayout.WEST);
        mainFrame.add(this.gameMsgPanel,BorderLayout.NORTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void initPlayers()
    {
        if (players[0].getName().length() == 0 || players[0].getName().toUpperCase().startsWith("IA") ) {
            String name = (players[1].getName().length()> 0)?players[1].getName():"IA0";
            Player[] modes = {new Minimax(1,name),new Negamax(1, name), new Alphabeta(1, name)};
            Player iaPlayer = (Player) JOptionPane.showInputDialog(null, "Choisir le type d'IA du joueur " + players[0].getName(),
                    "Type de l'IA", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
            controller.setPlayer(iaPlayer,0);
        }else{
            controller.setPlayer(new StdPlayer(players[0].getName()),0);
        }
        if (players[1].getName().length() == 0 || players[1].getName().toUpperCase().startsWith("IA")) {
            String name = (players[1].getName().length()> 0)?players[1].getName():"IA1";
            Player[] modes = {new Minimax(-1, name),new Negamax(-1, name), new Alphabeta(-1, name)};
            Player iaPlayer = (Player) JOptionPane.showInputDialog(null, "Choisir le type d'IA du joueur " + name,
                    "Type de l'IA", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
            controller.setPlayer(iaPlayer,1);
        }else{
            controller.setPlayer(new StdPlayer(players[1].getName()),1);
        }
    }

    private void setupPlayerPanel(){
        players[0] = new PlayerLabel(0);
        currentPlayer = players[0];

        players[1] = new PlayerLabel(1);
        initPlayers();
        this.playersPanel = new JPanel();
        this.playersPanel.setLayout(new GridLayout(1,2));
        this.playersPanel.add(players[0]);
        this.playersPanel.add(players[1]);
    }
    private void setupBoardPanel(){
        this.boardPanel = new BoardPanel(controller.getSizes(),controller,this);
    }
    private void setupInfoPanel(){
        this.infoPanel = new InfoPanel(controller, level);
    }
    private void setupFreePieceList(){
        piecePanel = new PieceListPanel(controller.getPieces(),controller);
    }
    private void setupGameTxt(){ gameMsgPanel = new GameMsgPanel(controller);}

    public void updateSelectedPiece(String namePiece){
        infoPanel.updateSelectedPiece(namePiece);
    }
    public void refresh(){
        updateSelectedPiece(controller.getSelectedPieceName());
        this.boardPanel.refreshBoard();
        this.piecePanel.refreshPieces();

        this.playersPanel.revalidate();
        this.boardPanel.revalidate();
        this.infoPanel.revalidate();
        this.gameMsgPanel.revalidate();
        this.piecePanel.revalidate();

        this.playersPanel.repaint();
        this.boardPanel.repaint();
        this.infoPanel.repaint();
        this.gameMsgPanel.repaint();
        this.piecePanel.repaint();

    }

    public static int initLevel(){
        Integer[] levels = {1,2,3,4};
        Integer level = (int) JOptionPane.showInputDialog(null, "Choisir le niveau du jeu :",
                "Choix du niveau", JOptionPane.QUESTION_MESSAGE, null, levels, levels[0]);
        return level;
    }
    public void endGame(){
        JOptionPane.showMessageDialog(new JPanel(),
                    "Fin de la partie. Le Gagnant est "+ controller.getCurrentPlayerName());
        if (JOptionPane.showConfirmDialog(null, "Voulez-vous recommencer une partie ?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            mainFrame.dispose();
            controller.newGame();

        }else{
            gameMsgPanel.setEndMsg();
        }

    }

    public void setTxtPlacePiece() {
        gameMsgPanel.setTxtPlacePiece();
    }
    public void setTxtSelectPiece() {
        gameMsgPanel.setTxtSelectPiece();
    }

    public void addHistorySelectedPiece(String namePiece){
        infoPanel.addHistorySelectedPiece(namePiece);
    }
    public void addHistoryPlace(int x, int y){
        infoPanel.addHistoryPlace(x,y);
    }


    public void switchPlayer() {
        currentPlayer.inactive();
        currentPlayer = (currentPlayer == players[0])?players[1]:players[0];
        currentPlayer.active();
    }
}
