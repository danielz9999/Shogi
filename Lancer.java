import java.util.ArrayList;

public class Lancer extends piece{
    public Lancer(int player, int xCor, int yCor) {
        super(player, xCor, yCor);
    }
    @Override
    public String getType() {
        return "lancer";
    }
    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {

        if (this.getOwner() == 0) {
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int Xless = x - 1;
            int breaker = 0;
            if (Xless == 0) {
                moves = null;
                return  moves;
            }
            for (int i = 0; i <= Xless; i++) {
                int xAdjust = x - i - 1;
                moves.add(new Coordinates(xAdjust,y));
                for (int j = 0; j < whitePositions.size(); j++) {
                    if (whitePositions.get(j).firstHalf() == xAdjust && whitePositions.get(j).secondHalf() == y) {
                        moves.remove(i);
                        breaker = 1;
                        break;
                    }
                }
                for (int k = 0; k < blackPositions.size(); k++) {
                    if (blackPositions.get(k).firstHalf() == xAdjust && blackPositions.get(k).secondHalf() == y) {
                        breaker = 1;
                        break;
                    }
                }
                if (breaker == 1) {
                    break;
                }
            }
            moves = friendBlock(moves, whitePositions);
            System.out.println(moves.size());
            return moves;
        } else if (this.getOwner() == 1){
            ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
            int Xless = 8 - x;
            int breaker = 0;
            if (Xless == 0) {
                moves = null;
                return  moves;
            }
            for (int i = 0; i < Xless; i++) {
                int xAdjust = x + i + 1;
                moves.add(new Coordinates(xAdjust,y));
                for (int j = 0; j < blackPositions.size(); j++) {
                    if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == y) {
                        moves.remove(i);
                        breaker = 1;
                        break;
                    }
                }
                for (int k = 0; k < whitePositions.size(); k++) {
                    if (whitePositions.get(k).firstHalf() == xAdjust && whitePositions.get(k).secondHalf() == y) {
                        breaker = 1;
                        break;
                    }
                }
                if (breaker == 1) {

                    break;
                }
            }
            moves = friendBlock(moves, blackPositions);
            System.out.println(moves.size());
            return moves;
        }
        return null;
    }
}
