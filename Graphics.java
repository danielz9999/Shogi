import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Graphics {



    public Graphics() {
        //GameMenu menu = new GameMenu();







        JFrame frame = new JFrame("Shogi");

        JButton exit = new JButton();
        JButton play = new JButton();
        JButton instructions = new JButton();
        frame.setSize(250,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        play.setText("Play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame playspace = new JFrame("Playing board");
                playspace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                playspace.setSize(800, 800);
                playspace.setLocationRelativeTo(null);
                playspace.setLayout(new GridLayout(9, 9));
                Board board = new Board();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        int l = i + 1;
                        int k = j + 1;
                        playspace.add(board.allButtons[i][j]);
                    }
                }
                playspace.setVisible(true);
                frame.dispose();
            }
        });
        play.setAlignmentX(frame.getContentPane().CENTER_ALIGNMENT);

        exit.setText("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        exit.setAlignmentX(frame.getContentPane().CENTER_ALIGNMENT);

        instructions.setText("Instructions");
        instructions.setAlignmentX(frame.getContentPane().CENTER_ALIGNMENT);

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0,10)));
        frame.getContentPane().add(play);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0,25)));
        frame.getContentPane().add(instructions);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0,25)));
        frame.getContentPane().add(exit);



        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    public static void main(String[] args) {

        Graphics gr = new Graphics();


    }

}
