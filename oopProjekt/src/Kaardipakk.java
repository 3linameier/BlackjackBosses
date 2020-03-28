import java.util.Arrays;

public class Kaardipakk {


    public static Kaart[] kaardipakk(){
        Kaart[] kaardid = new Kaart[52];

        int number = 1;
        int mast = 0;
        for (int i = 0; i < 52; i++) {
            Kaart uusKaart = new Kaart(number, mast);
            kaardid[i] = uusKaart;
            if(number==13) {
                number = 1;
                mast++;
            }
            else
                number++;
        }
        return kaardid;
    }

    @Override
    public String toString() {
        return "Kaardipakk: "+ Arrays.toString(kaardipakk());
    }
}
