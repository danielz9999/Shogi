public class piece {
    private int x;
    private int y;
    private int owner;

    public piece(int player, int xCor, int yCor) {
        owner = player;
        x = xCor;
        y = yCor;
    }
    public void moveTo(int destinationX, int destinationY) {
        x = destinationX;
        y = destinationY;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getType() {
        return null;
    }
}
