package view;

import controller.QuartoController;

import javax.swing.*;
import java.awt.*;

public class GameMsgPanel extends JPanel {

    private QuartoController controller;

    private JTextArea txtGameMsg;
    private JButton btnStart;


    public GameMsgPanel(QuartoController controller){
        this.controller = controller;

        txtGameMsg = new JTextArea();
        txtGameMsg.setText("Début de la partie");
        txtGameMsg.setEditable(false);
        txtGameMsg.setFont(new Font("Serif",Font.BOLD,30));
        btnStart = new JButton("Commencer une partie");
        btnStart.addActionListener(e ->{
            controller.startGame();
            setTxtSelectPiece();
            btnStart.setEnabled(false);
            btnStart.setVisible(false);

        });
        this.add(txtGameMsg);
        this.add(btnStart);
    }

    public void setTxtPlacePiece() {
        txtGameMsg.setText("Placer la pièce sélectionner sur le plateau.");
    }
    public void setTxtSelectPiece() {
        txtGameMsg.setText("Sélectionner une pièce pour votre adversaire");
    }

}
