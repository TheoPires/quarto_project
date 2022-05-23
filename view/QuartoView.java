package view;

import IA.Algorithm;
import IA.Alphabeta;
import IA.Minimax;
import IA.Negamax;
import controller.QuartoController;
import model.Player;

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
    private JTextArea txtGameMsg;

    private PlayerLabel[] players = new PlayerLabel[2];
    private PlayerLabel currentPlayer;

    /**
     * Initializes all the components of the GUI and puts them into a JFrame to display.
     */
    public QuartoView(QuartoController controller)
    {
        this.controller = controller;
        this.mainFrame = new JFrame("Quarto !");
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        setupPlayerPanel();
        mainFrame.add(this.playersPanel,BorderLayout.SOUTH);
        init();
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

    public void newGame(){
        mainFrame.remove(this.boardPanel);
        mainFrame.remove(this.infoPanel);
        mainFrame.remove(this.piecePanel);
        mainFrame.remove(this.gameMsgPanel);
        init();
    }
    private void initCPUs()
    {

        if (players[0].getName().toUpperCase().startsWith("IA") || players[0].getName().length() == 0 ) {
            Player[] modes = {new Minimax(1),new Negamax(1), new Alphabeta(1)};
            Player iaPlayer = (Player) JOptionPane.showInputDialog(null, "Choose the difficulty of " + players[0].getName(),
                    "IA difficulty selection", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
            controller.setPlayer(iaPlayer);
        }
        if (players[1].getName().toUpperCase().startsWith("IA") || players[1].getName().length() == 0) {
            Player[] modes = {new Minimax(-1),new Negamax(-1), new Alphabeta(-1)};
            Player iaPlayer = (Player) JOptionPane.showInputDialog(null, "Choose the difficulty of " + players[1].getName(),
                    "IA difficulty selection", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
            controller.setPlayer(iaPlayer);
        }
    }

    private void setupPlayerPanel(){
        players[0] = new PlayerLabel(0);
        currentPlayer = players[0];

        players[1] = new PlayerLabel(1);
        initCPUs();
        this.playersPanel = new JPanel();
        this.playersPanel.setLayout(new GridLayout(1,2));
        this.playersPanel.add(players[0]);
        this.playersPanel.add(players[1]);
    }
    private void setupBoardPanel(){
        this.boardPanel = new BoardPanel(controller.getSizes(),controller,this);
    }
    private void setupInfoPanel(){
        this.infoPanel = new InfoPanel(controller);
    }
    private void setupFreePieceList(){
        piecePanel = new PieceListPanel(controller.getPieces(),controller,this);
    }
    private void setupGameTxt(){ gameMsgPanel = new GameMsgPanel(controller);}

    public void updateSelectedPiece(String namePiece){
        infoPanel.updateSelectedPiece(namePiece);
    }
    public void refresh(){
        this.boardPanel.refreshBoard();

        this.playersPanel.revalidate();
        this.boardPanel.revalidate();
        this.infoPanel.revalidate();
        this.gameMsgPanel.revalidate();

        this.playersPanel.repaint();
        this.boardPanel.repaint();
        this.infoPanel.repaint();
        this.gameMsgPanel.repaint();

    }

    public void endGame(){
        JOptionPane.showMessageDialog(new JPanel(),
                    "Fin de la partie.");

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
