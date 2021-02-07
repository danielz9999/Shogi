public class Pawn extends piece {
    public Pawn (int hrac) {
        super(hrac);
        isTest = false;
        numberOfMoves = 1;
        isTaken = false;
        isNull = false;

    }
    @Override
    public Dvojice[] getMoves(int x, int y) {
        if (owner == 0) {
            Dvojice[] ara = new Dvojice[1];
            int newX = x - 1;
            ara[0] = new Dvojice(newX, y);
            System.out.println("UP");
            return ara;

        } else if (owner == 1) {
           Dvojice[] ara = new Dvojice[1];
           int newX = x + 1;
           ara[0] = new Dvojice(newX, y);
           System.out.println("DOWN");
           return ara;

        } else {
            Dvojice[] ara = new Dvojice[0];
            return ara;
        }
    }
}
