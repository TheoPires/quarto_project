package view;

import controller.QuartoController;

import javax.swing.*;
import java.awt.*;

public class QuartoView {

    private QuartoController controller;

    private JFrame mainFrame;
    private JPanel playersPanel,gameMsgPanel;
    private BoardPanel boardPanel;
    private InfoPanel infoPanel;
    private PieceListPanel piecePanel;
    private JTextArea txtGameMsg;


    private PlayerLabel player0;
    private PlayerLabel player1;

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
        setupBoardPanel();
        setupInfoPanel();
        setupFreePieceList();
        setupGameTxt();
        mainFrame.add(this.playersPanel,BorderLayout.SOUTH);
        mainFrame.add(this.boardPanel,BorderLayout.CENTER);
        mainFrame.add(this.infoPanel,BorderLayout.EAST);
        mainFrame.add(this.piecePanel,BorderLayout.WEST);
        mainFrame.add(this.gameMsgPanel,BorderLayout.NORTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    private void setupPlayerPanel(){
        player0 = new PlayerLabel(0);
        player1 = new PlayerLabel(1);
        this.playersPanel = new JPanel();
        this.playersPanel.setLayout(new GridLayout(1,2));
        this.playersPanel.add(player0);
        this.playersPanel.add(player1);
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
    private void setupGameTxt(){
        gameMsgPanel = new JPanel();
        txtGameMsg = new JTextArea();
        txtGameMsg.setText("Début de la partie");
        txtGameMsg.setEditable(false);
        txtGameMsg.setFont(new Font("Serif",Font.BOLD,30));
        JButton btn = new JButton("Commencer une partie");
        btn.addActionListener(e ->{

            controller.startGame();
            txtGameMsg.setText("Sélectionner une pièce");
            btn.setEnabled(false);
            btn.setVisible(false);

        });
        gameMsgPanel.add(txtGameMsg);
        gameMsgPanel.add(btn);
    }

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

    public void setTxtPlacePiece() {
        txtGameMsg.setText("Placer la pièce sélectionner sur le plateau.");
    }
    public void setTxtSelectPiece() {
        txtGameMsg.setText("Sélectionner une pièce pour votre adversaire");
    }

    public void addHistorySelectedPiece(String namePiece){
        infoPanel.addHistorySelectedPiece(namePiece);
    }
    public void addHistoryPlace(int x, int y){
        infoPanel.addHistoryPlace(x,y);
    }
}
