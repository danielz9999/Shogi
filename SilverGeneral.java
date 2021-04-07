import java.util.ArrayList;

public class SilverGeneral extends piece{
    public SilverGeneral(int player, int xCor, int yCor) {
        super(player, xCor, yCor);
        setUpgradePiece("golden");
    }
    @Override
    public String getType() {
        return "silver";
    }
    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        if (this.getOwner() == 0) {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int Xless = x - 1;
            int Xmore = x + 1;
            int Yless = y - 1;
            int Ymore = y + 1;
            moves.add(new Coordinates(Xmore,Yless));
            moves.add(new Coordinates(Xmore,Ymore));
            moves.add(new Coordinates(Xless,Ymore));
            moves.add(new Coordinates(Xless,Yless));
            moves.add(new Coordinates(Xless,y));
            moves = friendBlock(moves, whitePositions);
            moves = outOfBoundsFilter(moves);
            moves = nullifier(moves);
            return moves;
        } else {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int Xless = x - 1;
            int Xmore = x + 1;
            int Yless = y - 1;
            int Ymore = y + 1;
            moves.add(new Coordinates(Xmore,Yless));
            moves.add(new Coordinates(Xmore,Ymore));
            moves.add(new Coordinates(Xless,Ymore));
            moves.add(new Coordinates(Xless,Yless));
            moves.add(new Coordinates(Xmore,y));
            moves = friendBlock(moves, blackPositions);
            moves = outOfBoundsFilter(moves);
            moves = nullifier(moves);
            return moves;
        }

    }
}
