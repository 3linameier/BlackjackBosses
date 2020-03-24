public class Kaart {

    public static final String[] numbrid = new String[]{null, "äss", "kaks", "kolm", "neli", "viis", "kuus", "seitse", "kaheksa",
            "üheksa", "kümme", "poiss", "emand", "kuningas"};
    public static final String[] mastid = new String[]{"Ärtu", "Ruutu", "Risti", "Poti"};

    private int number;
    private int mast;

    public int getNumber() {
        return number;
    }

    public int getMast() {
        return mast;
    }

    public Kaart(int number, int mast) {
        this.number = number;
        this.mast = mast;
    }


    @Override
    public String toString() {
        return  mastid[this.mast] + " " + numbrid[this.number];
    }
}
