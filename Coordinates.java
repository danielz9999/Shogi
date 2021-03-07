public class Coordinates {
    private int a;
    private int b;

    public Coordinates(int partA, int partB) {
        a = partA;
        b = partB;
    }
    public int firstHalf() {
        return a;
    }
    public int secondHalf() {
        return b;
    }

}
