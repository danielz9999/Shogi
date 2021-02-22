public class King extends piece{
    public King (int hrac) {
        super(hrac);
        isTest = false;
        numberOfMoves = 8;
        isTaken = false;
        isNull = false;

    }
    @Override
    public Dvojice[] getMoves(int x, int y) {
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
        return outcomeMoves;
    }
}
