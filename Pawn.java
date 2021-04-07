import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int player, int xCor, int yCor) {
        super(player,xCor,yCor);
        setUpgradePiece("golden");
    }
    @Override
    public String getType() {
        return "pawn";
    }

    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        if (this.getOwner() == 0) {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int newX = x - 1;
            moves.add(new Coordinates(newX,y));
            moves = friendBlock(moves, whitePositions);
            moves = nullifier(moves);
            return moves;
        } else {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int newX = x + 1;
            moves.add(new Coordinates(newX,y));
            moves = friendBlock(moves, blackPositions);
            moves = nullifier(moves);
            return moves;
        }

    }
}
