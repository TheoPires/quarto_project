package view;

import controller.QuartoController;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private QuartoController controller;
    private JTabbedPane infoOnglet;
    private JPanel rulesPanel, historyPanel,selectedPiecePanel;
    private JLabel selectedPieceImage;

    private JTextArea historyText;

    public InfoPanel (QuartoController controller){
        this.controller = controller;
        this.setLayout(new BorderLayout());
        infoOnglet = new JTabbedPane();

        setupRulesPanel();
        setupHistoryPanel();
        setupPieceSelected();

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
        JScrollPane scroll = new JScrollPane(historyText);
        historyPanel.add(scroll);
    }

    //public void addTextInHistory(String msg){ historyText.append(msg);}

    private void setupPieceSelected() {
        selectedPiecePanel = new JPanel();
        selectedPieceImage = new JLabel("Aucune pièce sélectionner");
        selectedPiecePanel.setLayout(new BorderLayout());
        selectedPiecePanel.add(selectedPieceImage,BorderLayout.CENTER);
    }

    public void updateSelectedPiece(String img){
        selectedPiecePanel.remove(selectedPieceImage);
        if(img == null || img.equals("")){
            selectedPieceImage = new JLabel("Aucune pièce sélectionner");
        }else {
            selectedPieceImage = new JLabel(new ImageIcon("img/" + img + ".png"));
        }
        selectedPiecePanel.add(selectedPieceImage, BorderLayout.CENTER);
    }

    public void addHistoryPlace(int x, int y){
        historyText.append("La pièce a été placé en ["+x+", "+y+"].\n");
    }
    public void addHistorySelectedPiece(String namePiece){
        historyText.append("La pièce ["+namePiece+"] a été sélectionner.\n");
    }

}
