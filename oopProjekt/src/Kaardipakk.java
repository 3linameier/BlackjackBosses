import java.util.ArrayList;

public class Kaardipakk {

    /**
     * Meetod teeb Kaart isenditest kaardipaki, kus on igat isendit neli korda ehk neli kaardipakki
     * tagastab kaardipaki ArrayListina
     */
    public static ArrayList<Kaart> kaardipakk() {
        ArrayList<Kaart> kaardid = new ArrayList<>();
        for (int i = 0; i < 4; i++) { // teeme neli kaardipakki
            for (int j = 1; j < 13; j++) {
                for (int k = 1; k < 4; k++) {
                    if (j >= 10) {
                        Kaart uusKaart = new Kaart(j, k, 10);
                        kaardid.add(uusKaart);

                    } else if (j == 1) {
                        Kaart uusKaart = new Kaart(j, k, 11);
                        kaardid.add(uusKaart);
                    } else {
                        Kaart uusKaart = new Kaart(j, k, j);
                        kaardid.add(uusKaart);
                    }
                }
            }
        }

        return kaardid;
    }
}
