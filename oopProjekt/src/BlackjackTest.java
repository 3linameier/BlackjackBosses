import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class BlackjackTest {


    public static void main(String[] args) throws Exception {
        int kordused;
        int mangijaHitLimit;
        int diilerHitLimit;

        System.out.println("Tere tulemast!");
        System.out.println("Mängime blackjacki!");

        Thread.sleep(1000);

        System.out.print("Sisesta numbriga, mitu korda soovid mängu läbi teha(1-Integer.MAX_VALUE): ");
        try (Scanner scan1 = new Scanner(System.in)) {
            kordused = scan1.nextInt();

            System.out.print("Sisesta numbriga, mida soovid mängija hitimise laeks(soovitatavalt vahemikus 16-20): ");
            Scanner scan2 = new Scanner(System.in);
            mangijaHitLimit = scan2.nextInt();

            System.out.print("Sisesta numbriga, mida soovid diileri hitimise laeks(soovitatavalt vahemikus 16-20): ");
            Scanner scan3 = new Scanner(System.in);
            diilerHitLimit = scan3.nextInt();

            int[] tulemused = mangimangu(kordused, mangijaHitLimit, diilerHitLimit);
            /*
            for (int value : tulemused) {
                System.out.println(value);
            }

             */
            int protsent = tulemused[0]/kordused*100;

            System.out.println("Tegin mängu läbi "+kordused+" korda. Nendest võitsid sina "+tulemused[0]+" korral, kaotasid "+
                    tulemused[2]+" korral ning jäid diileriga viiki "+tulemused[1]+" korral.\nMangija HIT piiri "+mangijaHitLimit+
                            " ning diileri HIT piiri "+diilerHitLimit+" korral moodustasid sinu võidud kogu mängudest" +
                    " seega "+protsent+"%.");


        } catch (Exception e) {
            System.out.println("Midagi läks untsu!");
            e.printStackTrace();
        }
    }//main


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

    /**
     * meeetodi kehas toimub kaardimängu mängimine vastavalt kasutaja sisestatud kordadele
     * @param kordused mitu korda mäng läbi tehakse
     * @param mangijaHitLimit kuna mängija lõpetab hitimise
     * @param diilerHitLimit kuna diiler lõpetab hittimise
     * @return tagastab massiivi, kus on mängija võitude, viikide, kaotuste arvud
     */
    public static int[] mangimangu(int kordused, int mangijaHitLimit, int diilerHitLimit) {
        int[] tulemused = new int[3];
        int voidud = 0;
        int kaotused = 0;
        int viik = 0;

        ArrayList<Kaart> kaardipakk = kaardipakk(); //tehakse mängu uus kaaridpakk

        for (int i = 0; i < kordused; i++) { // mäng käib nii mitu korda nagu kasutaja ütles

            //siit edasi on üks mäng kuni kas 21ni või mängija bustimiseni.
            boolean diilerStand = false;
            boolean mangijaStand = false; // Tõeväärtused, kas mängu mingiks hetkeks on diiler või mängija bustinud või passivad või on blackjack.
            boolean mangijaBust = false;
            boolean diilerBust = false;
            boolean mangijaBJack = false;
            boolean diilerBJack = false;

            if (kaardipakk.size() < 12) { // Selleks, et saaks vähemalt ühe mängu veel ära mängida.
                kaardipakk = kaardipakk();
            }
            ArrayList<Kaart> diilerKaardid = new ArrayList<>(); // Massiiv diileri kaartide hoiustamiseks
            ArrayList<Kaart> mangijaKaardid = new ArrayList<>(); // Massiiv mängija kaartide hoiustamiseks

            //Diileri ja mängija kaartide koguväärtus ehk kaartide numbrite summa
            int mangijaVaartus = 0;
            int diilerVaartus = 0;

            Random rand = new Random();

            //Kaartide indeksid kaardipaki massiivis
            int mangijaKaartIx = 0;
            int diilerKaartIx = 0;

            int mAssadeArv = 0;
            int dAssadeArv = 0;

            for (int j = 0; j < 2; j++) { // Lisab mängijale ja diilerile kaks suvalist kaarti.
                mangijaKaartIx = rand.nextInt(kaardipakk.size());
                mangijaKaardid.add(kaardipakk.get(mangijaKaartIx));
                if (kaardipakk.get(mangijaKaartIx).getNumber()==1) mAssadeArv +=1;
                kaardipakk.remove(mangijaKaartIx); // Võetud kaardid kustutatakse pakist, et ei tekiks korrdusi rohkem kui pakis on.
                diilerKaartIx = rand.nextInt(kaardipakk.size());
                diilerKaardid.add(kaardipakk.get(diilerKaartIx));
                if (kaardipakk.get(diilerKaartIx).getNumber()==1) dAssadeArv +=1;
                kaardipakk.remove(diilerKaartIx);
            }

            //Kuvab lihtsalt kaardid
            System.out.println();
            System.out.println("Diileril on käes " + diilerKaardid.get(0).toString() + " ja " + diilerKaardid.get(1).toString());
            System.out.println("Sul on käes " + mangijaKaardid.get(0).toString() + " ja " + mangijaKaardid.get(1).toString());
            System.out.println();

            mangijaVaartus += mangijaKaardid.get(0).getVaartus() + mangijaKaardid.get(1).getVaartus();
            diilerVaartus += diilerKaardid.get(0).getVaartus() + diilerKaardid.get(1).getVaartus();

            //MÄNGIJA
            if (mangijaVaartus>=mangijaHitLimit) {
                if (mangijaVaartus==21) mangijaBJack = true;
                if (mangijaVaartus>21) mangijaBust = true;
                System.out.println("Su kaartide väärtus: "+mangijaVaartus+", STAND.");
                mangijaStand = true;
            } else {
                while (!mangijaStand && !mangijaBJack && !mangijaBust) { // mäng lõpeb siis kui mängija kaartide väärtus on üle 17.
                    //Mängija hitib, ehk ta kätte lisandub üks kaart pakist.
                    System.out.println("HIT");
                    mangijaKaartIx = rand.nextInt(kaardipakk.size());
                    Kaart uus = kaardipakk.get(mangijaKaartIx);
                    if (kaardipakk.get(mangijaKaartIx).getNumber()==1) mAssadeArv += 1;
                    mangijaKaardid.add(uus);
                    System.out.println(uus);
                    mangijaVaartus += uus.getVaartus();
                    kaardipakk.remove(mangijaKaartIx);
                    System.out.println("Nüüd on su kaartide väärtus: " + mangijaVaartus);



                    while (mangijaVaartus >= mangijaHitLimit) {
                        if (mangijaVaartus == 21) {
                            mangijaBJack = true;
                            break;
                        }
                        if (mangijaVaartus > 21) {
                            if (mAssadeArv>0) {
                                for (int j = 0; j < mangijaKaardid.size(); j++) {
                                    if (mangijaKaardid.get(j).getNumber() == 1) {
                                        mangijaVaartus -= 10;
                                        mAssadeArv -= 1;
                                        System.out.println("Oli äss, võtsin 10 väärtusest maha");
                                        break;
                                    }
                                }
                            }
                            if (mangijaVaartus>=mangijaHitLimit) {
                                mangijaBust = true;
                                break;
                            }
                        } else {
                            mangijaStand = true;
                            break;
                        }
                    }
                }
            }

            //DIILER
            if (diilerVaartus>=diilerHitLimit) {
                if (diilerVaartus==21) diilerBJack = true;
                if (diilerVaartus>21) diilerBust = true;
                System.out.println("Diileri kaartide väärtus: "+diilerVaartus+", STAND.");
                diilerStand = true;
            } else {
                while (!diilerStand && !diilerBJack && !diilerBust) { // mäng lõpeb siis kui mängija kaartide väärtus on üle 17.
                    //Mängija hitib, ehk ta kätte lisandub üks kaart pakist.
                    System.out.println("HIT");
                    diilerKaartIx = rand.nextInt(kaardipakk.size());
                    Kaart uus = kaardipakk.get(diilerKaartIx);
                    if (kaardipakk.get(diilerKaartIx).getNumber()==1) dAssadeArv += 1;
                    diilerKaardid.add(uus);
                    System.out.println(uus);
                    diilerVaartus += uus.getVaartus();
                    kaardipakk.remove(diilerKaartIx);
                    System.out.println("Nüüd on diileri kaartide väärtus: " + diilerVaartus);


                    while (diilerVaartus >= diilerHitLimit) {
                        if (diilerVaartus == 21) {
                            diilerBJack = true;
                            break;
                        }
                        if (diilerVaartus > 21) {
                            if (dAssadeArv>0) {
                                for (int j = 0; j < diilerKaardid.size(); j++) {
                                    if (diilerKaardid.get(j).getNumber() == 1) {
                                        diilerVaartus -= 10;
                                        dAssadeArv -= 1;
                                        System.out.println("Oli äss, võtsin kümme väärtusest maha");
                                        break;
                                    }
                                }
                            }
                            if (diilerVaartus>=diilerHitLimit) {
                                diilerBust = true;
                                break;
                            }
                        } else {
                            diilerStand = true;
                            break;
                        }
                    }
                }
            }

            //Võit või kaotus
            if (mangijaBust) {
                if (diilerBust) {
                    System.out.println("Viik");
                    viik += 1;
                } else {
                    System.out.println("Sina kaotasid");
                    kaotused += 1;
                }
            }
            if (!mangijaBust) {
                if (diilerBust) {
                    System.out.println("OFOOFOSinu võit");
                    voidud += 1;
                } else if (mangijaBJack) {
                    if (diilerBJack) {
                        System.out.println("Viik");
                        viik += 1;
                    }
                    else {
                        System.out.println("sinu BLACKJACK!!");
                        voidud += 1;
                    }
                } else {
                    System.out.println("Su vaartus: " + mangijaVaartus);
                    System.out.println("Diileri vaartus: " + diilerVaartus);
                    if (mangijaVaartus > diilerVaartus) {
                        System.out.println("Sina voitsid");
                        voidud += 1;
                    } else if (mangijaVaartus==diilerVaartus) {
                        System.out.println("Viik");
                        viik += 1;
                    } else {
                        System.out.println("Sina kaotasid");
                        kaotused += 1;
                    }
                }
            }
        }
        tulemused[0] = voidud;
        tulemused[1] = viik;
        tulemused[2] = kaotused;
        return tulemused;
    }
}
