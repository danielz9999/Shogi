import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics {

    JButton vsechnyButtons[][] = new TheButtons[9][9];

    JFrame plocha = new JFrame("Hrací plocha");


    public Graphics() {
        GameRules g = new GameRules();


        plocha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plocha.setSize(800, 800);
        plocha.setLocationRelativeTo(null);
        plocha.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;



                TheButtons buttons = new TheButtons(i, j);
                System.out.println(buttons.x + " " + buttons.y);
                buttons.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("soudj");
                        g.possibleMoves(buttons.x, buttons.y);
                    }
                });
                vsechnyButtons[i][j] = buttons;
                plocha.add(vsechnyButtons[i][j]);
            }
        }

        plocha.setVisible(true);




    }

    public static void main(String[] args) {
;
        Graphics gr = new Graphics();
    }
}

