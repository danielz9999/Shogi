//prázndá figurka, která se nachází na všech prázdných polích

public class nullPiece extends piece {
    public nullPiece() {
        isTest = false;
        isNull = true;
    }

    @Override
    public String toString() {
        return "null piece";
    }

}
