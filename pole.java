

public class pole {
    int coorX;
    int coorY;
    piece currentPiece;

    public pole (int indexA, int indexB, piece Piece) {
        coorX = indexA;
        coorY = indexB;
        currentPiece = Piece;
    }

    public void whereAmI() {
        this.currentPiece.currentPole = this;
    }


}
