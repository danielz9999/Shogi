import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics {
    GameRules g = new GameRules();
    JButton vsechnyButtons[][] = new TheButtons[9][9];

    JFrame plocha = new JFrame("Hrací plocha");

    Dvojice[] moveList;

    int stareX;
    int stareY;

    public Graphics() {

        g.gameSpace.vsechnyPole[4][4].currentPiece = new testPiece();
        g.gameSpace.vsechnyPole[4][4].whereAmI();
        g.gameSpace.vsechnyPole[5][5].currentPiece = new testPiece();
        g.gameSpace.vsechnyPole[4][4].whereAmI();

        plocha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plocha.setSize(800, 800);
        plocha.setLocationRelativeTo(null);
        plocha.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;



                TheButtons buttons = new TheButtons(i, j);
                vsechnyButtons[i][j] = buttons;
                plocha.add(vsechnyButtons[i][j]);
                System.out.println(buttons.x + " " + buttons.y);
                buttons.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //System.out.println(g.isClicked);
                        boolean saveFalsity = false;
                        if (g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isTest == false) {
                            saveFalsity = true;
                        }
                        //System.out.println(g.isMovePossible(buttons.x, buttons.y, g.possibleMoves(buttons.x, buttons.y)));


                        if (!g.isClicked && g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isTest == true && g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isTaken != true) {
                            stareX = buttons.x;
                            stareY = buttons.y;
                            moveList = g.possibleMoves(stareX, stareY);
                            System.out.println("proběhlo");
                        }
                        if (g.isMovePossible(buttons.x, buttons.y, moveList) && g.isClicked) {
                            g.gameSpace.moveTo(g.gameSpace.vsechnyPole[stareX][stareY].currentPiece, g.gameSpace.vsechnyPole[buttons.x][buttons.y]);
                            poleUpdate(g.gameSpace.vsechnyPole[stareX][stareY]);
                            poleUpdate(g.gameSpace.vsechnyPole[buttons.x][buttons.y]);
                            System.out.println("all good");
                        } else {
                            System.out.println("no good");

                        }
                        if (g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isTest == true && g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isTaken != true) {
                            if (saveFalsity) {
                                g.isClicked = false;
                            } else {
                                g.isClicked = true;
                            }
                        } else {
                            g.isClicked = false;
                        }

                    }
                });

            }
        }
        poleUpdate(g.gameSpace.vsechnyPole[4][4]);
        poleUpdate(g.gameSpace.vsechnyPole[5][5]);
        plocha.setVisible(true);




    }

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

    public void takeFigurka() {

    }

    public static void main(String[] args) {
;
        Graphics gr = new Graphics();

    }
}

