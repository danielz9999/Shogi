import javax.swing.*;
//třída která se stará o JPanel plocha a o vytvoření a spravování políček


public class Board {

    public pole vsechnyPole[][] = new pole[9][9];
    JButton vsechnyButtons[][] = new TheButtons[9][9];

    //JFrame plocha = new JFrame("Hrací plocha");


    public Board() {



        /*plocha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plocha.setSize(800, 800);
        plocha.setLocationRelativeTo(null);
        plocha.setLayout(new GridLayout(9, 9));
        */
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;
                nullPiece NULL = new nullPiece();
                vsechnyPole[i][j] = new pole(l, k, NULL);
                vsechnyPole[i][j].whereAmI();


            }
        }


        /*vsechnyPole[4][4].currentPiece = new testPiece();
        vsechnyPole[4][4].whereAmI();
        System.out.println(vsechnyPole[4][4].currentPiece.x);
        plocha.setVisible(true);
        poleUpdate(vsechnyPole[4][4]);

        moveTo(vsechnyPole[4][4].currentPiece, vsechnyPole[5][5]);
        */


    }

   /* public void poleUpdate(pole p) {
        int x = p.coorX - 1;
        int y = p.coorY - 1;

        if (p.currentPiece.isTest == true) {
            vsechnyButtons[x][y].setBackground(Color.BLACK);
            vsechnyButtons[x][y].setOpaque(true);
        } else if (p.currentPiece.isTest == false) {
            vsechnyButtons[x][y].setBackground(Color.RED);
            vsechnyButtons[x][y].setOpaque(true);
        }
    }
*/
    //funkce která hýbe s figurkami
    public void moveTo(piece a, pole b) {
        int x = a.currentPole.coorX - 1;
        int y = a.currentPole.coorY - 1;
        int c = b.coorX - 1;
        int d = b.coorY - 1;

        a.currentPole = vsechnyPole[c][d];
        vsechnyPole[c][d].currentPiece.isTaken = true;
        vsechnyPole[x][y].currentPiece = new nullPiece();
        vsechnyPole[c][d].currentPiece = a;
        //poleUpdate(vsechnyPole[c][d]);
        //poleUpdate(vsechnyPole[x][y]);

    }

 //pokus o přepsání moveTo2 funkce, momentálně nevyužita
   /* public void moveTo2(pole a, pole b) {
        int x = a.coorX - 1;
        int y = a.coorY - 1;
        int c = b.coorX - 1;
        int d = b.coorY - 1;

        vsechnyPole[c][d] = a;
        //vsechnyPole[c][d].currentPiece.isTaken = true;
        vsechnyPole[c][d].currentPiece = a.currentPiece;
        vsechnyPole[x][y] = new pole(x+1,y+1,new nullPiece());

    }


    */


}
