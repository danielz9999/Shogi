import javax.swing.*;
import java.awt.*;

public class Graphics {

    JFrame playspace = new JFrame("Playing board");

    public Graphics() {
        Board board = new Board();
        playspace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playspace.setSize(800, 800);
        playspace.setLocationRelativeTo(null);
        playspace.setLayout(new GridLayout(9, 9));



        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;
                playspace.add(board.allButtons[i][j]);
            }
        }

        playspace.setVisible(true);

    }
    public static void main(String[] args) {

        Graphics gr = new Graphics();

    }
}
