//tato třída slouží k uložení dvojice souřadnic pro jednoduché porovnání v funkci moveTo() ve třídě board

public class Dvojice {
    private int a;
    private int b;

    public Dvojice(int prvekA, int prvekB) {
        a = prvekA;
        b = prvekB;
    }
    public int firstHalf() {
        return a;
    }
    public int secondHalf() {
        return b;
    }
}
