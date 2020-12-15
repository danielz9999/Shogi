

public class GameRules {
    //private pole[][] b = new pole[8][8];
    private pole laasClick;
    private pole[] moves;
    private boolean isClicked;
    Board gameSpace = new Board();

    public GameRules() {

    }

    public pole[][] possibleMoves(int x, int y) {
        System.out.println("tady");
        System.out.println(gameSpace.vsechnyPole[2][2].coorX);
        System.out.println(x + " " + y);
        System.out.println(gameSpace.vsechnyPole[x][y].currentPiece.toString());

        // pokud true projít jestli v moves je momentalní políčko s x,y, pokud ano, tak moveTo z last click do daného pole
        if (isClicked) {

        } else {

        }
        return null;
    }
    public void getBoard(pole[][] p) {
        //b = p;
    }




}
