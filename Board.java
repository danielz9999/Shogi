import javax.swing.*;
import java.awt.*;

public class Board {
    JButton allButtons[][] = new TheButtons[9][9];
    Space allSpaces[][] = new Space[9][9];

    public Board() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;

                //vytvoří se 81 tlačítek, která jsou rozmístěna na plochu v 9x9 síti
                TheButtons buttons = new TheButtons(i, j);
                allButtons[i][j] = buttons;
                allSpaces[i][j] = new Space();
                allSpaces[i][j].changePiece(new NullPiece(i,j));
            }
        }
        makePawn(1,5,5);
    }

    public void movePiece(piece movingPiece, int x, int y) {
        allSpaces[movingPiece.getX()][movingPiece.getY()].changePiece(new NullPiece(movingPiece.getX(),movingPiece.getY()));
        buttonUpdate(movingPiece.getX(),movingPiece.getY());
        movingPiece.moveTo(x,y);
        allSpaces[x][y].changePiece(movingPiece);
        buttonUpdate(x,y);
    }
    public void buttonUpdate(int x, int y) {
        if (allSpaces[x][y].getCurrentPiece().getType() != "null") {
            allButtons[x][y].setBackground(Color.BLACK);
            allButtons[x][y].setOpaque(true);
        }
    }
    public void makePawn(int owner, int x, int y) {
        allSpaces[x][y].changePiece(new Pawn(owner,x,y));
        buttonUpdate(x,y);
    }
}
