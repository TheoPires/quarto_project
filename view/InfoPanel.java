package view;

import controller.QuartoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class InfoPanel extends JPanel {

    private JTabbedPane infoOnglet;
    private JPanel rulesPanel, historyPanel,selectedPiecePanel;
    private ImageIcon selectedPieceImage;

    private JTextArea historyText;

    public InfoPanel (){
        this.setLayout(new BorderLayout());
        infoOnglet = new JTabbedPane();

        setupRulesPanel();
        setupHistoryPanel();
        setupPieceSelected();
        addTextInHistory("Joueur 1 à jouer.\n");

        infoOnglet.setBounds(40,20,300,300);

        infoOnglet.add("Règles", rulesPanel);
        infoOnglet.add("Historique", historyPanel);

        this.add(infoOnglet,BorderLayout.CENTER);
        this.add(selectedPiecePanel,BorderLayout.SOUTH);
    }

    public void setupRulesPanel(){
        rulesPanel = new JPanel();
        String rules = "Voici les règles du jeu Quarto:\n -Placer des pièces pour effectuer un alignement\n";
        JTextArea textArea = new JTextArea();
        textArea.append(rules);
        textArea.setEditable(false);
        rulesPanel.add(textArea);
    }
    private void setupHistoryPanel() {
        historyPanel = new JPanel(new BorderLayout());
        historyText = new JTextArea();
        historyText.setEditable(false);
        historyPanel.add(historyText);
    }

    public void addTextInHistory(String msg){
        historyText.append(msg);
    }

    private void setupPieceSelected() {
        selectedPiecePanel = new JPanel();
        selectedPieceImage = new ImageIcon( "img/big_bwn_cir.png");
        JLabel image = new JLabel( selectedPieceImage);
        selectedPiecePanel.setLayout(new BorderLayout());
        selectedPiecePanel.add(image,BorderLayout.CENTER);
    }

    public void updateSelectedPiece(String img){
        selectedPieceImage= new ImageIcon(img);
    }

}
