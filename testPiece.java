//prozatimní testovací třída,reprezentována černým čtverečkem, která se může hýbat do všech směrů o jedno pole


public class testPiece extends piece {

    public testPiece(int hrac) {
        super(hrac);
        isTest = true;
        numberOfMoves = 8;
        isTaken = false;
        isNull = false;

    }
    @Override
    public String toString() {
        return "test piece";
    }
}
