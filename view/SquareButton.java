package view;

import javax.swing.*;
import java.awt.*;
public class SquareButton extends JButton{

    public final int row;
    public final int col;
    Color defaultClr;
    String namePiece;

    public SquareButton(int row, int col, Color color,String namePiece) {
        super();
        this.row = row;
        this.col = col;
        this.defaultClr = (color == null)? new Color(148, 93, 53) : color;
        this.namePiece = namePiece;

        setPreferredSize(new Dimension(60,60));
        setBackground(defaultClr);
        setContentAreaFilled(false);
        setOpaque(true);
        setMargin(new Insets(0,0,0,0));
        setFocusPainted(false);
    }
    public boolean haveNamePieceNull(){
        return namePiece == null || namePiece.equals("");
    }

    public void setIcon(Icon icon,String namePiece){
        super.setIcon(icon);
        this.namePiece = namePiece;
    }

    /*public void highLight()
    {
        setBackground(Color.GREEN);
    }

    public void unHighLight()
    {
        setBackground(defaultClr);
    }*/

    public String getNamePiece() {
            return this.namePiece;
    }
    public int getRow() { return row;}

    public int getCol() { return col;}
}

