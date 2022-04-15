package view;

import model.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PieceListPanel extends JPanel {
    private SquareButton[] pieces;
    private int NB_COLOR = 2;

    private SquareButton selectedSquareButton;

    public PieceListPanel(List<Piece> lstPiece){
        pieces = new SquareButton[lstPiece.size()];
        this.setLayout(new GridLayout(lstPiece.size()/NB_COLOR,NB_COLOR,10,10));
        this.setBackground(Color.white);
        System.out.println(lstPiece.size());
        for(int i = 0; i<lstPiece.size();i=i+2){
            SquareButton btn1 = new SquareButton(0,i,Color.WHITE);
            btn1.setIcon(new ImageIcon("img/"+lstPiece.get(i).getNameImage()+".png"));
            SquareButton btn2 = new SquareButton(1,i+1,Color.WHITE);
            btn2.setIcon(new ImageIcon("img/"+lstPiece.get(i+1).getNameImage()+".png"));
            btn1.addActionListener(e -> {
                if(selectedSquareButton != null)
                    selectedSquareButton.unHighLight();
                selectedSquareButton = btn1;
                btn1.highLight();
            });
            btn2.addActionListener(e -> {
                if(selectedSquareButton != null)
                    selectedSquareButton.unHighLight();
                selectedSquareButton = btn2;
                btn2.highLight();
            });
            this.add(btn1);
            this.add(btn2);
            pieces[i]= btn1;
            pieces[i+1]= btn2;
            System.out.println("img/"+lstPiece.get(i).getNameImage()+".png");
            System.out.println("img/"+lstPiece.get(i+1).getNameImage()+".png");

        }
    }

}
