public class Space {
    private piece currentPiece;
    public Space() {

    }
    public void changePiece(piece newPiece) {
        currentPiece = newPiece;
    }
    public piece getCurrentPiece() {
        return currentPiece;
    }
}
