import java.util.ArrayList;

public class King extends piece{
    public King(int player, int xCor, int yCor) {
        super(player, xCor, yCor);
        setUpgradePiece("non upgradable");
    }
    @Override
    public String getType() {
        return "king";
    }
    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {

            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int Xless = x - 1;
            int Xmore = x + 1;
            int Yless = y - 1;
            int Ymore = y + 1;
            moves.add(new Coordinates(Xless, Yless));
            moves.add(new Coordinates(Xless, y));
            moves.add(new Coordinates(x, Yless));
            moves.add(new Coordinates(Xless, Ymore));
            moves.add(new Coordinates(Xmore, y));
            moves.add(new Coordinates(x,Ymore));
            moves.add(new Coordinates(Xmore,Yless));
            moves.add(new Coordinates(Xmore,Ymore));
            moves = friendBlock(moves, whitePositions);
            return moves;



    }
}
