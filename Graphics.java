import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class Graphics {



    public Graphics() {
        //GameMenu menu = new GameMenu();
        int whiteTime = 0;
        int blackTime = 0;
        int turn = 0;






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
                int input1 = JOptionPane.showConfirmDialog(null, "Would you like to play a standard game? (No timer, player 1//bottom player starts)", "Standard option", JOptionPane.YES_NO_OPTION);
                if (input1 == 0 ) {
                    boardSetup(0,0,0);
                } else if (input1 == 1) {
                    int input2 = JOptionPane.showConfirmDialog(null, "Would you like to play with a timer?", "Timer option", JOptionPane.YES_NO_OPTION);
                    if (input2 == 0 ) {
                        int whiteTime = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter time (in seconds) for player 1 (max. 3559)"));
                        if (whiteTime > 3559) {
                            overMaximum();
                            whiteTime = 3599;
                        }
                        int blackTime = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter time (in seconds) for player 2 (max. 3559)"));
                        if (blackTime > 3559) {
                            overMaximum();
                            blackTime = 3599;
                        }
                        int input3 = JOptionPane.showConfirmDialog(null, "Would you player 1 // bottom player to start?", "Player option", JOptionPane.YES_NO_OPTION);
                        if (input3 == 0) {
                            boardSetup(whiteTime,blackTime,0);
                        } else if (input3 == 1) {
                            boardSetup(whiteTime,blackTime,1);
                        }
                    } else if (input2 == 1) {
                        int input4 = JOptionPane.showConfirmDialog(null, "Would you player 1 // bottom player to start?", "Player option", JOptionPane.YES_NO_OPTION);
                        if (input4 == 0) {
                            boardSetup(0,0,0);
                        } else if (input4 == 1) {
                            boardSetup(0,0,1);
                        }
                    }
                }

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
    public static void main(String[] args)  {

        Graphics gr = new Graphics();


    }
    public void boardSetup(int timeOne, int timeTwo, int player) {
        JFrame playspace = new JFrame("Playing board");
        playspace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playspace.setSize(800, 800);
        playspace.setLocationRelativeTo(null);
        playspace.setLayout(new GridLayout(9, 9));
        Board board = new Board(timeOne,timeTwo,player);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int l = i + 1;
                int k = j + 1;
                playspace.add(board.allButtons[i][j]);
            }
        }
        playspace.setVisible(true);

    }
    public void overMaximum() {
        JFrame frame = new JFrame();
        JButton button = new JButton();

        button.setText("Value inputted is over maximum threshold, defaulting to 3599");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
