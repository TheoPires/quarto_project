package view;

import controller.QuartoController;
import model.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieceListPanel extends JPanel {

    private QuartoController controller;


    private List<SquareButton> buttons = new ArrayList<>();

    private int NB_COLOR = 2;


    public PieceListPanel(List<Piece> lstPiece,QuartoController controller){
        this.controller = controller;
        this.setLayout(new GridLayout(lstPiece.size()/NB_COLOR,NB_COLOR,10,10));
        this.setBackground(Color.red);
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
                            controller.setSelectedPiece(btn.getNamePiece());
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

    public void setEnableSquareButton(Piece p){
        for(SquareButton squareButton : buttons){
            if(squareButton.getNamePiece().equals(p.getNameImage()))
                squareButton.setEnabled(true);
        }
    }
    public void setDisableSquareButton(Piece p){
        for(SquareButton squareButton : buttons){
            if(squareButton.getNamePiece().equals(p.getNameImage()))
                squareButton.setEnabled(false);
        }
    }

    public void refreshPieces() {
        List<Piece> pieceNotPlaced = controller.getPieces();
        for(Piece p : Piece.values()){
            if(pieceNotPlaced.contains(p)){
                setEnableSquareButton(p);
            }else{
                setDisableSquareButton(p);
            }

        }
    }
}
