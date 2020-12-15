public class piece {
    String owner;
    pole currentPole;
    boolean isTest;
    int x;
    int y;


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
