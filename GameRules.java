import java.util.ArrayList;

public class GameRules {
    private int currentTurn;
    public GameRules(){
        currentTurn = 0;
    }
    public boolean isMovePossible(int x, int y, ArrayList<Coordinates> p, int moves) {

        for (int i = 0; i < moves; i++) {

            if (x == p.get(i).firstHalf() && y == p.get(i).secondHalf()) {
                return true;
            }

        }
        System.out.println("Move is not possible");
        return false;
    }
    public int getCurrentTurn() {
        return currentTurn;
    }
    public void setCurrentTurn(int a) {
        currentTurn = a;
    }
}
