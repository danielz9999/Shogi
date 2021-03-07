import javax.swing.*;

public class TheButtons extends JButton {
    private int a;
    private int b;
    public TheButtons(int first, int second) {
        a = first;
        b = second;
    }
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
}
