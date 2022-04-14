package view;

import controller.QuartoController;

import javax.swing.*;
import java.awt.*;

public class QuartoView {

    private QuartoController controller;

    private JFrame mainFrame;
    private JPanel playersPanel,boardPanel;
    private InfoPanel infoPanel;
    private PieceListPanel piecePanel;


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
        mainFrame.add(this.playersPanel,BorderLayout.SOUTH);
        mainFrame.add(this.boardPanel,BorderLayout.CENTER);
        mainFrame.add(this.infoPanel,BorderLayout.EAST);
        mainFrame.add(this.piecePanel,BorderLayout.WEST);

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
        this.boardPanel = new BoardPanel(controller.getSizes());
    }
    private void setupInfoPanel(){
        this.infoPanel = new InfoPanel();
    }
    private void setupFreePieceList(){
        piecePanel = new PieceListPanel();

    }


}
