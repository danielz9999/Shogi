import java.util.ArrayList;

public class piece {
    private int x;
    private int y;
    private int owner;
    private String upgradePiece;

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
    public void setUpgradePiece(String s) {
        upgradePiece = s;
    }
    public String getUpgradePiece() {
        return upgradePiece;
    }
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        return null;
    }
    //checks whether a position inside the movelist is out of bounds and if so, removes it
    public ArrayList<Coordinates> outOfBoundsFilter(ArrayList<Coordinates> p ) {
        ArrayList<Integer> outside = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).firstHalf() < 0 || p.get(i).firstHalf() > 8 || p.get(i).secondHalf() < 0 || p.get(i).secondHalf() > 8) {
                p.remove(i);
                i--;
            }
        }

        return  p;
    }
    //return null when passed empty ArrayLists, because somehow Arraylists with the size of 0 were not being counted as null
    public ArrayList<Coordinates> nullifier(ArrayList<Coordinates> p) {
        if (p.size() == 0) {
            p = null;
        }
        return p;
    }
    //removes friendly positions from the movelist passed to it
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
