public class GoldGeneral extends piece{
    public GoldGeneral (int hrac) {
        super(hrac);
        isTest = false;
        numberOfMoves = 6;
        isTaken = false;
        isNull = false;

    }
    @Override
    public Dvojice[] getMoves(int x, int y) {
        if (owner == 0) {
            Dvojice[] ara = new Dvojice[numberOfMoves];
            int Xless = x - 1;
            int Xmore = x + 1;
            int Yless = y - 1;
            int Ymore = y + 1;
            ara[0] = new Dvojice(Xless, Yless);
            ara[1] = new Dvojice(Xless, y);
            ara[2] = new Dvojice(x, Yless);
            ara[3] = new Dvojice(Xless, Ymore);
            ara[4] = new Dvojice(Xmore, y);
            ara[5] = new Dvojice(x,Ymore);
            return ara;

        } else if (owner == 1) {
            Dvojice[] ara = new Dvojice[numberOfMoves];
            int Xmore = x + 1;
            int Xless = x - 1;
            int Yless = y - 1;
            int Ymore = y + 1;
            ara[0] = new Dvojice(Xmore, Yless);
            ara[1] = new Dvojice(Xless, y);
            ara[2] = new Dvojice(x, Yless);
            ara[3] = new Dvojice(Xmore, Ymore);
            ara[4] = new Dvojice(Xmore, y);
            ara[5] = new Dvojice(x,Ymore);
            return ara;

        } else {
            Dvojice[] ara = new Dvojice[0];
            return ara;
        }
    }
}
