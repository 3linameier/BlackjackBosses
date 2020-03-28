public class BlackjackTest {
    public static void main(String[] args) {
        //Kaart potiKuus = new Kaart(6, 3);

        //System.out.println(potiKuus); // Printis: Poti kuus


        for (Kaart kaart : kaardipakk()) {
            System.out.println(kaart);
        }


    }

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
}


// Et oleks parem tegeleda nende kaartidega siis teeme süsteemi nii, et numbrid on nagu ikka ja poiss on 11, emand on 12 ja kunn on 13
// Mastide jaoks paneme ka numbrid, siis saab nagu võrrelda vni. Ärtu on 0, ruutu on 1, risti on 2, poti on 3

