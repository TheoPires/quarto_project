package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {

    private SquareButton selectedSquareButton;

    public BoardPanel(int size){
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
                 SquareButton sqBtn = new SquareButton(i,j,color);
                 sqBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                 sqBtn.addActionListener(e -> {
                     if(selectedSquareButton != null)
                        selectedSquareButton.unHighLight();
                     selectedSquareButton = sqBtn;
                     sqBtn.highLight();
                 });
                this.add(sqBtn);
            }
        }
    }

    public void setColor(SquareButton btn){
    }
}
