import java.util.ArrayList;

public class Rook extends piece{

    public Rook(int player, int xCor, int yCor) {
        super(player, xCor, yCor);
        setUpgradePiece("unupgraded");
    }

    @Override
    public String getType() {
        return "rook";
    }
    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        ArrayList<Coordinates> moves =  new ArrayList<Coordinates>();;
        int northReps = x;
        int southReps = 8 - x;
        int eastReps = 8 - y;
        int westReps = y;
        int breaker = 0;
        System.out.println("north: " + northReps + " south: " + southReps + " west: " + westReps + " east: " + eastReps);


        //north move
        for (int i = 0; i < northReps; i++) {
            int xAdjust = x - i - 1;
            moves.add(new Coordinates(xAdjust,y));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == y) {

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
        breaker = 0;
        //south move
        for (int i = 0; i < southReps; i++) {
            int xAdjust = x + i + 1;
            moves.add(new Coordinates(xAdjust,y));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == y) {

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
        breaker = 0;
        //west move
        for (int i = 0; i < westReps; i++) {
            int yAdjust = y - i - 1;
            moves.add(new Coordinates(x,yAdjust));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == x && blackPositions.get(j).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            for (int k = 0; k < whitePositions.size(); k++) {
                if (whitePositions.get(k).firstHalf() == x && whitePositions.get(k).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            if (breaker == 1) {

                break;
            }
        }
        breaker = 0;
        //east move
        for (int i = 0; i < eastReps; i++) {
            int yAdjust = y + i + 1;
            moves.add(new Coordinates(x,yAdjust));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == x && blackPositions.get(j).secondHalf() == yAdjust) {
                    System.out.println(i + j);
                    breaker = 1;
                    break;
                }
            }
            for (int k = 0; k < whitePositions.size(); k++) {
                if (whitePositions.get(k).firstHalf() == x && whitePositions.get(k).secondHalf() == yAdjust) {
                    System.out.println(i + " " + k);
                    breaker = 1;
                    break;
                }
            }
            if (breaker == 1) {

                break;
            }
        }
        if (this.getUpgradePiece().equals("upgraded")) {
            System.out.println("UPGRADED!");
            moves.add(new Coordinates(x - 1, y - 1));
            moves.add(new Coordinates(x + 1, y - 1));
            moves.add(new Coordinates(x - 1, y + 1));
            moves.add(new Coordinates(x + 1, y + 1));
        }
        if (this.getOwner() == 0) {
            moves = friendBlock(moves, whitePositions);
        } else if (this.getOwner() == 1) {
            moves = friendBlock(moves, blackPositions);
        }
        return moves;
    }
}
