public class NullPiece extends piece {

    public NullPiece(int xCor, int yCor) {
        super(2,xCor,yCor);
        setUpgradePiece("non upgradable");

    }
    @Override
    public String getType() {
        return "null";
    }
}
