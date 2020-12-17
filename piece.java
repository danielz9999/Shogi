//obecná parent třída všech figurek

public class piece {
    String owner;
    pole currentPole;
    boolean isTest;
    int x;
    int y;
    int numberOfMoves;
    String typFigurky;
    boolean isTaken;
    boolean isNull;

    public piece() {

    }
    public void whereIsPiece() {
        x = this.currentPole.coorX;
        y = this.currentPole.coorY;
    }
    public void polePieceUpdate() {
        currentPole.currentPiece = this;
    }
}
