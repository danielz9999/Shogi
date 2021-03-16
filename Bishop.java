import java.util.ArrayList;

public class Bishop extends piece{
    public Bishop(int player, int xCor, int yCor) {
        super(player, xCor, yCor);
    }
    @Override
    public String getType() {
        return "bishop";
    }
    @Override
    public ArrayList<Coordinates> getMoves(int x, int y, ArrayList<Coordinates> whitePositions, ArrayList<Coordinates> blackPositions) {
        ArrayList<Coordinates> moves =  new ArrayList<Coordinates>();;
        int northWestReps = 10;
        int southWestReps = 10;
        int northEastReps = 10;
        int southEastReps = 10;
        int breaker = 0;

        for (int w = 0;  w < 9; w++) {
            int xCheck;
            int yCheck;
            xCheck = x - w;
            yCheck = y - w;
            System.out.println(xCheck);
            if (xCheck == 0 || yCheck == 0) {
                northWestReps = w;
                break;
            }
        }
        for (int wc = 0;  wc < 9; wc++) {
            int xCheck;
            int yCheck;
            xCheck = x + wc;
            yCheck = y - wc;
            if (xCheck == 8 || yCheck == 0) {
                southWestReps = wc;
                break;
            }
        }
        for (int wa = 0;  wa < 9; wa++) {
            int xCheck;
            int yCheck;
            xCheck = x + wa;
            yCheck = y + wa;
            if (xCheck == 8 || yCheck == 8) {
                southEastReps = wa;
                break;
            }
        }
        for (int wb = 0;  wb < 9; wb++) {
            int xCheck;
            int yCheck;
            xCheck = x - wb;
            yCheck = y + wb;
            if (xCheck == 0 || yCheck == 8) {
                northEastReps = wb;
                break;
            }
        }
        System.out.println("northwest: " + northWestReps + " southwest: " + southWestReps + " southeast: " + southEastReps + " northeast: " + northEastReps);

        //northwest move
        for (int i = 0; i < northWestReps; i++) {
            int xAdjust = x - i - 1;
            int yAdjust = y - i - 1;
            moves.add(new Coordinates(xAdjust,yAdjust));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            for (int k = 0; k < whitePositions.size(); k++) {
                if (whitePositions.get(k).firstHalf() == xAdjust && whitePositions.get(k).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            if (breaker == 1) {

                break;
            }
        }
        breaker = 0;
        //southwest move
        for (int i = 0; i < southWestReps; i++) {
            int xAdjust = x + i + 1;
            int yAdjust = y - i - 1;
            moves.add(new Coordinates(xAdjust,yAdjust));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            for (int k = 0; k < whitePositions.size(); k++) {
                if (whitePositions.get(k).firstHalf() == xAdjust && whitePositions.get(k).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            if (breaker == 1) {

                break;
            }
        }
        breaker = 0;
        //southeast move
        for (int i = 0; i < southEastReps; i++) {
            int xAdjust = x + i + 1;
            int yAdjust = y + i + 1;
            moves.add(new Coordinates(xAdjust,yAdjust));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            for (int k = 0; k < whitePositions.size(); k++) {
                if (whitePositions.get(k).firstHalf() == xAdjust && whitePositions.get(k).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            if (breaker == 1) {

                break;
            }
        }
        breaker = 0;
        //northeast move
        for (int i = 0; i < northEastReps; i++) {
            int xAdjust = x - i - 1;
            int yAdjust = y + i + 1;
            moves.add(new Coordinates(xAdjust,yAdjust));
            for (int j = 0; j < blackPositions.size(); j++) {
                if (blackPositions.get(j).firstHalf() == xAdjust && blackPositions.get(j).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            for (int k = 0; k < whitePositions.size(); k++) {
                if (whitePositions.get(k).firstHalf() == xAdjust && whitePositions.get(k).secondHalf() == yAdjust) {

                    breaker = 1;
                    break;
                }
            }
            if (breaker == 1) {

                break;
            }
        }
        if (this.getOwner() == 0) {
            moves = friendBlock(moves, whitePositions);
        } else if (this.getOwner() == 1) {
            moves = friendBlock(moves, blackPositions);
        }
        return moves;
    }
}
