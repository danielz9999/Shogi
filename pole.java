//třída, která reprezentuje jednotlivá políčka, visuálně je potom reprezentována tlačítky TheButtons (obou je jich 81 a jsou ve dvourozměrném poli, tedy jejich indexy se shodují)

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
