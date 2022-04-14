package view;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel(int size){
        this.setLayout(new GridLayout(size,size));
        for(int i = 0; i < size;i++){
            for(int j = 0; j < size;j++){
                Color color;
                this.add(new SquareButton(i,j,Color.black));
            }
        }
    }
}
