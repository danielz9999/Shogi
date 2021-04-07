import java.util.ArrayList;

public class Knight extends piece {
    public Knight(int player, int xCor, int yCor) {
        super(player, xCor, yCor);
        setUpgradePiece("golden");
    }

    @Override
    public String getType() {
        return "knight";
    }

    ;
    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        if (this.getOwner() == 0) {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int newX = x - 2;
            int Yless = y - 1;
            int Ymore = y + 1;
            moves.add(new Coordinates(newX,Yless));
            moves.add(new Coordinates(newX,Ymore));
            moves = friendBlock(moves, whitePositions);
            moves = outOfBoundsFilter(moves);
            moves = nullifier(moves);
            return moves;
        } else {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int newX = x + 2;
            int Yless = y - 1;
            int Ymore = y + 1;
            moves.add(new Coordinates(newX,Yless));
            moves.add(new Coordinates(newX,Ymore));
            moves = friendBlock(moves, blackPositions);
            moves = outOfBoundsFilter(moves);
            moves = nullifier(moves);
            return moves;
        }

    }
}
