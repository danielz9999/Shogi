public class Knight extends piece{
    public Knight (int hrac) {
        super(hrac);
        isTest = false;
        numberOfMoves = 2;
        isTaken = false;
        isNull = false;

    }
    @Override
    public Dvojice[] getMoves(int x, int y) {
        if (owner == 0) {
            Dvojice[] ara = new Dvojice[numberOfMoves];
            int newX = x - 2;
            int Yless = y - 1;
            int Ymore = y + 1;
            ara[0] = new Dvojice(newX, Yless);
            ara[1] = new Dvojice(newX, Ymore);
            return ara;

        } else if (owner == 1) {
            Dvojice[] ara = new Dvojice[numberOfMoves];
            int newX = x + 2;
            int Yless = y - 1;
            int Ymore = y + 1;
            ara[0] = new Dvojice(newX, Yless);
            ara[1] = new Dvojice(newX, Ymore);
            return ara;

        } else {
            Dvojice[] ara = new Dvojice[0];
            return ara;
        }
    }
}
