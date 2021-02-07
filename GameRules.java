//třída se stará o dodržení pravidel

public class GameRules {
    //private pole[][] b = new pole[8][8];

    private pole[] moves;
    public boolean isClicked;
    public int currentTurn;
    Board gameSpace = new Board();

    public GameRules() {
        isClicked  = false;
    }
    //generuje pole dvojic souřadnic políček, na které se daná figurka z daného pole může hýbat
    public Dvojice[] possibleMoves(int x, int y) {
        System.out.println(x + " " + y);
        System.out.println(gameSpace.vsechnyPole[x][y].currentPiece.toString());

        // pokud true projít jestli v moves je momentalní políčko s x,y, pokud ano, tak moveTo z last click do daného pole

            if (gameSpace.vsechnyPole[x][y].currentPiece.isNull == false && gameSpace.vsechnyPole[x][y].currentPiece.isTest) {
                int xLess = x - 1;
                int yLess = y - 1;
                int xMore = x + 1;
                int yMore = y + 1;

                int[] xMoves;
                int[] yMoves;
                xMoves = new int[]{xMore, x, xLess, x, xMore, xLess, xMore, xLess};
                yMoves = new int[]{y, yMore, y, yLess, yMore, yLess, yLess, yMore};

                Dvojice[] outcomeMoves = new Dvojice[xMoves.length];
                for (int i = 0; i < 8; i++) {
                    outcomeMoves[i] = new Dvojice(xMoves[i], yMoves[i]);
                }
                //pole[][] outcomeMoves;
                //outcomeMoves = new pole[8][2]{xMore, x, xLess, x, xMore, xLess, xMore, xLess, y, yMore, y, yLess, yMore, yLess, yLess, yMore};
                //outcomeMoves = new pole[]{gameSpace.vsechnyPole[xMore][y], gameSpace.vsechnyPole[x][yMore], gameSpace.vsechnyPole[xLess][y], gameSpace.vsechnyPole[x][yLess], gameSpace.vsechnyPole[xMore][yMore], gameSpace.vsechnyPole[xLess][yLess], gameSpace.vsechnyPole[xMore][yLess], gameSpace.vsechnyPole[xLess][yMore]};
                return outcomeMoves;
            } else if (!gameSpace.vsechnyPole[x][y].currentPiece.isNull && !gameSpace.vsechnyPole[x][y].currentPiece.isTest) {

                Dvojice[] outcomeMoves;
                outcomeMoves = gameSpace.vsechnyPole[x][y].currentPiece.getMoves(x, y);
                return outcomeMoves;
            } else {
                Dvojice[] prazdna = new Dvojice[0];
                return prazdna;
            }


    /*public void getBoard(pole[][] p) {
        //b = p;
    */}
    //funkce, která zjišťuje, zda je tah možný tím, že zkoumá, jestli jsou souřadnice daného cílového políčka součástí pole souřadic možnýhc pohybů původního políčka
    public boolean isMovePossible(int x, int y, Dvojice[] p, int moves) {
        for (int i = 0; i < moves; i++) {

            if (x == p[i].firstHalf() && y == p[i].secondHalf()) {
                return true;
            }

        }
        return false;
    }



}
