package view;

import controller.QuartoController;
import model.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieceListPanel extends JPanel {

    private QuartoController controller;
    private QuartoView view;

    private List<Piece> lastSave = new ArrayList<>();

    private List<SquareButton> buttons = new ArrayList<>();

    private int NB_COLOR = 2;

    private SquareButton selectedSquareButton;

    public PieceListPanel(List<Piece> lstPiece,QuartoController controller,QuartoView view){
        this.controller = controller;
        this.view  = view;
        this.setLayout(new GridLayout(lstPiece.size()/NB_COLOR,NB_COLOR,10,10));
        this.setBackground(Color.white);
        for(int i = 0; i < lstPiece.size();i++){
            this.add(initSquareButton(lstPiece.get(i).getNameImage()));
        }
    }

    public SquareButton initSquareButton(String namePiece){
        SquareButton btn = new SquareButton(0,0,Color.WHITE,namePiece);
        buttons.add(btn);
        btn.setIcon(new ImageIcon("img/"+namePiece+".png"));
        btn.addActionListener(e -> {

            if (controller.isGameStarted()) {
                if (controller.canSelectedNewPiece()) {
                        int res = JOptionPane.showConfirmDialog(this, "Etes-vous sur de sélectionner cette pièce?");

                        if (res == JOptionPane.YES_OPTION) {
                            selectedSquareButton = btn;
                            controller.setSelectedPiece(btn.getNamePiece());
                            view.addHistorySelectedPiece(selectedSquareButton.getNamePiece());
                            btn.setEnabled(false);
                        }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Erreur: Action non permise\n Voir le message en haut de page");
                }
            }else {
                JOptionPane.showMessageDialog(this,
                        "Veuillez démarrer une partie.");
            }
        });
        return btn;
    }


}
