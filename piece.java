import java.util.ArrayList;

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
    public int getOwner() {
        return owner;
    }

    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        return null;
    }
    public ArrayList<Coordinates> friendBlock(ArrayList<Coordinates> moves, ArrayList<Coordinates> friendlyPositions) {
        ArrayList<Coordinates> finalMoves;

        for (int i = 0; i < moves.size(); i++) {
            for (int j = 0; j < friendlyPositions.size() && i < moves.size(); j++) {

                if (moves.get(i).firstHalf() == friendlyPositions.get(j).firstHalf() && moves.get(i).secondHalf() == friendlyPositions.get(j).secondHalf()) {
                    moves.remove(i);
                    //i = i - 1;
                    j = 0;
                    if (moves.size() == 0) {
                        break;
                    }

                } else {

                }
            }
        }
        return moves;
    }
}
