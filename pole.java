

public class pole {
    public int coorX;
    public int coorY;
    public piece currentPiece;

    public pole (int indexA, int indexB, piece Piece) {
        coorX = indexA;
        coorY = indexB;
        currentPiece = Piece;
    }

    public void whereAmI() {
        this.currentPiece.currentPole = this;
    }


}
