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
        g.currentTurn = 0;
        //generování figurek
        g.gameSpace.vsechnyPole[4][4].currentPiece = new testPiece(1);
        g.gameSpace.vsechnyPole[4][4].whereAmI();
        g.gameSpace.vsechnyPole[5][5].currentPiece = new testPiece(0);
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


                //vytvoří se 81 tlačítek, která jsou rozmístěna na plochu v 9x9 síti
                TheButtons buttons = new TheButtons(i, j);
                vsechnyButtons[i][j] = buttons;
                plocha.add(vsechnyButtons[i][j]);
                //System.out.println(buttons.x + " " + buttons.y);
                //click event tlačítka
                buttons.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (moveList != null && g.isMovePossible(buttons.x, buttons.y, moveList, g.gameSpace.vsechnyPole[stareX][stareY].currentPiece.numberOfMoves) && g.gameSpace.vsechnyPole[stareX][stareY].currentPiece.owner == g.currentTurn) {
                            g.gameSpace.moveTo(g.gameSpace.vsechnyPole[stareX][stareY].currentPiece, g.gameSpace.vsechnyPole[buttons.x][buttons.y]);
                            poleUpdate(g.gameSpace.vsechnyPole[stareX][stareY]);
                            poleUpdate(g.gameSpace.vsechnyPole[buttons.x][buttons.y]);
                            moveList = null;
                            if (g.currentTurn == 0) {
                                    g.currentTurn = 1;

                            } else if (g.currentTurn == 1) {
                                    g.currentTurn = 0;

                            }
                        } else if (moveList != null && !g.isMovePossible(buttons.x, buttons.y, moveList, g.gameSpace.vsechnyPole[stareX][stareY].currentPiece.numberOfMoves) && !g.gameSpace.vsechnyPole[buttons.x][buttons.y].currentPiece.isNull == true) {
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
        makePawn(1,1,1);
        makePawn(7,7,0);
        makeKnight(1,4,1);
        makeSilverGeneral(2,2,0);
        makeSilverGeneral(2,8,1);
        makeGoldGeneral(3,3, 1);
        plocha.setVisible(true);

        /*System.out.println(g.gameSpace.vsechnyPole[4][4].currentPiece.owner);
        System.out.println(g.gameSpace.vsechnyPole[5][5].currentPiece.owner);
        System.out.println(g.currentTurn);
        */


    }
    //kontroluje zda má pole figurku, pokud ano tak ji zobrazí
    public void poleUpdate(pole p) {
        int x = p.coorX - 1;
        int y = p.coorY - 1;

        if (p.currentPiece.isTest == true && !p.currentPiece.isTaken) {
            vsechnyButtons[x][y].setBackground(Color.BLACK);
            vsechnyButtons[x][y].setOpaque(true);
        } else if (p.currentPiece.isNull == true) {
            vsechnyButtons[x][y].setBackground(Color.RED);
            vsechnyButtons[x][y].setOpaque(true);
        } else if (p.currentPiece.numberOfMoves == 2) {
            vsechnyButtons[x][y].setBackground(Color.DARK_GRAY);
        } else if (p.currentPiece.numberOfMoves == 5) {
            vsechnyButtons[x][y].setBackground(Color.cyan);
        }  else if (p.currentPiece.numberOfMoves == 6) {
            vsechnyButtons[x][y].setBackground(Color.magenta);
        } else {
            vsechnyButtons[x][y].setBackground(Color.BLUE);
        }
    }

    public void makePawn(int x, int y, int owner) {
        g.gameSpace.vsechnyPole[x][y].currentPiece = new Pawn(owner);
        g.gameSpace.vsechnyPole[x][y].whereAmI();
        poleUpdate(g.gameSpace.vsechnyPole[x][y]);
    }
    public void makeKnight(int x, int y, int owner) {
        g.gameSpace.vsechnyPole[x][y].currentPiece = new Knight(owner);
        g.gameSpace.vsechnyPole[x][y].whereAmI();
        poleUpdate(g.gameSpace.vsechnyPole[x][y]);
    }
    public void makeSilverGeneral(int x, int y, int owner) {
        g.gameSpace.vsechnyPole[x][y].currentPiece = new SilverGeneral(owner);
        g.gameSpace.vsechnyPole[x][y].whereAmI();
        poleUpdate(g.gameSpace.vsechnyPole[x][y]);
    }
    public void makeGoldGeneral(int x, int y, int owner) {
        g.gameSpace.vsechnyPole[x][y].currentPiece = new GoldGeneral(owner);
        g.gameSpace.vsechnyPole[x][y].whereAmI();
        poleUpdate(g.gameSpace.vsechnyPole[x][y]);
    }

    public static void main(String[] args) {
;
        Graphics gr = new Graphics();

    }
}

