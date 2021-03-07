import javax.swing.*;

public class TheButtons extends JButton {
    private int partA;
    private int partB;
    public TheButtons(int first, int second) {
        partA = first;
        partB = second;
    }
    public int getA() {
        return partA;
    }
    public int getB() {
        return partB;
    }
}
