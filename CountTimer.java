import javax.swing.*;

public class CountTimer extends Thread{
    private long whiteTime;
    private long blackTime;
    long absoluteTime;
    int currentTurn;
    int currentPlayer;
    int blackMins;
    int blackSec;
    int whiteMins;
    int whiteSec;

    public CountTimer(int timeOne, int timeTwo, int turn) {
        whiteTime = timeOne * 1000;
        blackTime = timeTwo * 1000;
        absoluteTime = System.currentTimeMillis();
        currentTurn = turn;
        currentPlayer = turn + 1;
        blackMins = timeTwo/60;
        blackSec = timeTwo - 60*blackMins;
        whiteMins = timeOne/60;
        whiteSec = timeOne - 60*whiteMins;


    }
    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setSize(300,90);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JLabel turnText = new JLabel("It is the turn of player: " + currentPlayer + "        ");

        JLabel label1 = new JLabel("Remaining time of player 1:     "+whiteMins+" : "+whiteSec);
        JLabel label2 = new JLabel("Remaining time of player 2:     "+blackMins+" : "+blackSec);

        frame.add(turnText);
        if (whiteTime > 0 && blackTime > 0 ){
            frame.add(label1);
            frame.add(label2);
        }


        frame.setVisible(true);

        if (whiteTime == 0 || blackTime == 0) {
            long timeGone = 0;
            frame.setSize(300,60);
            for (int i = 0; timeGone < 3559*1000; i++) {
                timeGone = System.currentTimeMillis() - absoluteTime;
                turnText.setText("      It is the turn of player: " + currentPlayer + "        ");

            }
        } else {

            for (int i = 0; whiteTime > 0 && blackTime > 0; i++) {
                if (currentTurn == 0) {
                    long timeElapsed = System.currentTimeMillis() - absoluteTime;
                    whiteTime = whiteTime - timeElapsed;
                    whiteSec = (int) whiteTime / 1000;
                    whiteMins = whiteSec / 60;
                    whiteSec = whiteSec - whiteMins * 60;
                    if (whiteTime % 100 == 0) {
                        if (whiteSec < 10) {
                            label1.setText("Remaining time of player 1:     " + whiteMins + " :  0" + whiteSec);
                        } else {
                            label1.setText("Remaining time of player 1:     " + whiteMins + " : " + whiteSec);
                        }
                    }
                    if (whiteTime < 0) {
                        label1.setText("        0");
                    }
                } else if (currentTurn == 1) {
                    long timeElapsed = System.currentTimeMillis() - absoluteTime;
                    blackTime = blackTime - timeElapsed;
                    blackSec = (int) blackTime / 1000;
                    blackMins = blackSec / 60;
                    blackSec = blackSec - blackMins * 60;
                    if (blackTime % 100 == 0) {


                        if (blackSec < 10) {
                            label2.setText("Remaining time of player 2:     " + blackMins + " :  0" + blackSec);
                        } else {
                            label2.setText("Remaining time of player 2:     " + blackMins + " : " + blackSec);
                        }
                    }
                    if (blackTime < 0) {
                        label2.setText("        0");
                    }
                }
                absoluteTime = System.currentTimeMillis();
                turnText.setText("      It is the turn of player: " + currentPlayer + "        ");
            }
        }



    }
    public void changeTurn() {
        if (currentTurn == 0 ) {
            currentTurn = 1;
            currentPlayer = 2;
        } else if (currentTurn == 1) {
            currentTurn = 0;
            currentPlayer = 1;
        }
    }
    public long getWhiteTime() {
        return whiteTime;
    }
    public long getBlackTime() {
        return blackTime;
    }
}

