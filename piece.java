//obecná parent třída všech figurek

public class piece {
    public int owner;
    pole currentPole;
    boolean isTest;
    int x;
    int y;
    int numberOfMoves;
    String typFigurky;
    boolean isTaken;
    boolean isNull;
    Dvojice[] moves = new Dvojice[numberOfMoves];

    public piece(int hrac) {
        owner = hrac;
    }
    public void whereIsPiece() {
        x = this.currentPole.coorX;
        y = this.currentPole.coorY;
    }
    public void polePieceUpdate() {
        currentPole.currentPiece = this;
    }

   public Dvojice[] getMoves(int x, int y) {
        Dvojice[] ara = new Dvojice[0];
        return ara;
   }
}
