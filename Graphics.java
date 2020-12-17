import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//klíčová a hlavní třída, stará se o zobrozování věci na JPanel plocha, spravuje tlačitka na obrazovce a prozatím zde má public static void main
public class Graphics {
    GameRules g = new GameRules();
    JButton vsechnyButtons[][] = new TheButtons[9][9];

    JFrame plocha = new JFrame("Hrací plocha");

    Dvojice[] moveList = null;

    int stareX;
    int stareY;


    public Graphics() {
        //vy generují se dvě figurky na polích 5,5 a 6,6
        g.gameSpace.vsechnyPole[4][4].currentPiece = new testPiece();
        g.gameSpace.vsechnyPole[4][4].whereAmI();
        g.gameSpace.vsechnyPole[5][5].currentPiece = new testPiece();
        g.gameSpace.vsechnyPole[5][5].whereAmI();
        //nastavení JPanelu plocha
        plocha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plocha.setSize(800, 800);
        plocha.setLocationRelativeTo(null);
        plocha.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;


                //vytvoří se 81 tlačítka, která jsou rozmístěna na plochu v 9x9 síti
                TheButtons buttons = new TheButtons(i, j);
                vsechnyButtons[i][j] = buttons;
                plocha.add(vsechnyButtons[i][j]);
                System.out.println(buttons.x + " " + buttons.y);
                //click event tlačítka
                buttons.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (moveList != null && g.isMovePossible(buttons.x, buttons.y, moveList)) {
                            g.gameSpace.moveTo(g.gameSpace.vsechnyPole[stareX][stareY].currentPiece, g.gameSpace.vsechnyPole[buttons.x][buttons.y]);
                            poleUpdate(g.gameSpace.vsechnyPole[stareX][stareY]);
                            poleUpdate(g.gameSpace.vsechnyPole[buttons.x][buttons.y]);
                            moveList = null;
                        } else if (moveList != null && !g.isMovePossible(buttons.x, buttons.y, moveList) && !g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isNull == true) {
                            stareX = buttons.x;
                            stareY = buttons.y;
                            moveList = g.possibleMoves(stareX, stareY);
                        } else if (moveList == null && g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isNull == false) {
                            stareX = buttons.x;
                            stareY = buttons.y;
                            moveList = g.possibleMoves(stareX, stareY);
                        }

                        else if (g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isNull == true) {
                            moveList = null;

                        }

                    }
                });

            }
        }
        poleUpdate(g.gameSpace.vsechnyPole[4][4]);
        poleUpdate(g.gameSpace.vsechnyPole[5][5]);
        plocha.setVisible(true);




    }
    //kontroluje zda má pole figurku, pokud ano tak ji zobrazí
    public void poleUpdate(pole p) {
        int x = p.coorX - 1;
        int y = p.coorY - 1;

        if (p.currentPiece.isTest == true && !p.currentPiece.isTaken) {
            vsechnyButtons[x][y].setBackground(Color.BLACK);
            vsechnyButtons[x][y].setOpaque(true);
        } else if (p.currentPiece.isTest == false) {
            vsechnyButtons[x][y].setBackground(Color.RED);
            vsechnyButtons[x][y].setOpaque(true);
        }
    }



    public static void main(String[] args) {
;
        Graphics gr = new Graphics();

    }
}

