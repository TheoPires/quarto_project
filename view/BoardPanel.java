package view;

import controller.QuartoController;
import model.Move;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private QuartoController controller;
    private SquareButton[][] boards;
    private QuartoView view;

    public BoardPanel(int size, QuartoController controller, QuartoView view){
        this.controller = controller;
        this.view = view;
        boards = new SquareButton[size][size];
        this.setLayout(new GridLayout(size,size));
        for(int i = 0; i < size;i++){
            for(int j = 0; j < size;j++){
                Color color = null;
                 if(i%2 == 0){
                     if(j%2 == 0)color = new Color(148, 93, 53);
                     else color= Color.white;
                 }else{
                     if(j%2 == 0)color= Color.white;
                     else color = new Color(148, 93, 53);
                 }
                 SquareButton sqBtn = new SquareButton(i,j,color,"");
                 sqBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                sqBtn.addActionListener(e -> {
                    if (controller.isGameStarted()) {
                        if (controller.canPlaceNewPiece()) {
                            if (sqBtn.haveNamePieceNull()) {
                                controller.setSelectedPieceSelectedPlace(sqBtn.getRow(), sqBtn.getCol());
                                view.addHistoryPlace(sqBtn.getRow(),sqBtn.getCol());
                            }else {
                                JOptionPane.showMessageDialog(this,
                                        "Erreur : Une pièce est déja placée sur cette case.");
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
                 boards[i][j] = sqBtn;
                this.add(sqBtn);
            }
        }
    }
    public void refreshBoard(){
        for(Move m : controller.getMoves()){
            this.boards[m.getX()][m.getY()].setIcon(
                    new ImageIcon("img/"+m.getPiece().getNameImage()+".png"),m.getPiece().getNameImage());
            //System.out.println("New image : " +m.getPiece().getNameImage());
        }
    }
}
