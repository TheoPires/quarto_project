package view;

import javax.swing.*;
import java.awt.*;

public class PieceListPanel extends JPanel {
    private final int NB_PIECE_SAME_COLOR= 8;
    private final int NB_COLOR= 2;
    private SquareButton[][] pieces;

    public PieceListPanel(){
        pieces = new SquareButton[NB_COLOR][NB_PIECE_SAME_COLOR];
        this.setLayout(new GridLayout(NB_PIECE_SAME_COLOR,NB_COLOR,10,10));
        this.setBackground(Color.white);

        for(int i = 0;i < NB_PIECE_SAME_COLOR;i++){
            SquareButton btn1 = new SquareButton(0, i, Color.YELLOW);
            SquareButton btn2 = new SquareButton(1, i, Color.BLUE);
            this.add(btn1);
            this.add(btn2);
            pieces[0][i] = btn1;
            pieces[1][i] = btn2;
        }
    }

}
