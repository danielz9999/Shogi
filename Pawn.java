public class Pawn extends piece{
    public Pawn(int player, int xCor, int yCor) {
        super(player,xCor,yCor);
    }
    @Override
    public String getType() {
        return "pawn";
    }
}
