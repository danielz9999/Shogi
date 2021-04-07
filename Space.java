public class Space {
    private Piece currentPiece;
    public Space() {

    }
    public void changePiece(Piece newPiece) {
        currentPiece = newPiece;
    }
    public Piece getCurrentPiece() {
        return currentPiece;
    }
}
