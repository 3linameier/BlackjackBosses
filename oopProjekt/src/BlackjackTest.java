import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackjackTest {


    public static void main(String[] args) throws Exception {
        int vastus;

        System.out.println("Tere tulemast!");
        System.out.println("Mängime blackjacki!");

        Thread.sleep(1000);

        System.out.print("Sisesta numbriga mitu korda soovid mängu läbi teha: ");
        try (Scanner scan = new Scanner(System.in)) {
            vastus = scan.nextInt();
        }
        mangimangu(vastus);

    }

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


    public static int mangimangu(int kordused) {
        int voidud = 0;

        ArrayList<Kaart> kaardipakk = kaardipakk();
        for (int i = 0; i < kordused; i++) {
            boolean diileristand=false;
            boolean mangijastand=false;
            boolean mangijabust=false;
            boolean diileribust=false;

            if (kaardipakk.size() < 4) {
                kaardipakk = kaardipakk();
            }
            ArrayList<Kaart> diiler = new ArrayList<>();
            ArrayList<Kaart> mangija = new ArrayList<>();
            int mangijavaartus = 0;
            int diilerivaartus = 0;
            int assadearv = 0;
            for (int j = 0; j < 2; j++) {
                Random randomint = new Random();
                int valik = randomint.nextInt(kaardipakk.size());
                mangija.add(kaardipakk.get(valik));
                kaardipakk.remove(valik);
                int valik2 = randomint.nextInt(kaardipakk.size());
                diiler.add(kaardipakk.get(valik2));
                kaardipakk.remove(valik2);

                System.out.println("Diileril on käes " + diiler.get(0).toString() + " ja " + "?");
                System.out.println("Sul on käes " + mangija.get(0).toString() + " ja " + mangija.get(1).toString());
                System.out.println();

                if ((mangija.get(0).getVaartus() + mangija.get(1).getVaartus()) == 21) {
                    System.out.println("Blackjack! Juhuu!");
                    System.out.println("Võitsid");
                    break;
                } else if ((diiler.get(0).getVaartus() + diiler.get(1).getVaartus()) == 21) {
                    System.out.println("Diileri Blackjack");
                    System.out.println("Kaotasid");
                    break;
                }

                while (!mangijastand) {
                    for (int k = 0; k < mangija.size(); k++) {
                        mangijavaartus += mangija.get(k).getVaartus();
                        if (mangija.get(k).getNumber() == 1) {
                            assadearv += 1;
                            if (mangijavaartus > 21) {
                                mangijavaartus -= 10 * assadearv;
                            }
                        }
                    }
                    System.out.println("Su kaartide väärtus on: " + mangijavaartus);
                    System.out.println("Hit v Stand?");
                    if (mangijavaartus < 17) {
                        System.out.println("Mängija HIT");
                        valik = randomint.nextInt(kaardipakk.size());
                        mangija.add(kaardipakk.get(valik));
                        kaardipakk.remove(valik);
                        mangijavaartus = 0;
                        for (int u = 0; u < mangija.size(); u++) {
                            mangijavaartus += mangija.get(u).getVaartus();
                        }
                        System.out.println("Su kaartide väärtus on: " + mangijavaartus);
                    }
                    if (mangijavaartus > 21) {
                        mangijabust = true;
                        System.out.println("Mängija Bust");
                        break;
                    } else {
                        mangijastand = true;
                        System.out.println("Mängja STAND");
                        System.out.println("Mängija lõplik kaardiväärtus on " + mangijavaartus);
                    }
                }

                while (!diileristand) {
                    for (int l = 0; l < diiler.size(); l++) {
                        diilerivaartus += diiler.get(l).getVaartus();
                        if (diiler.get(l).getNumber() == 1) {
                            assadearv += 1;
                            if (diilerivaartus > 21) {
                                diilerivaartus -= 10 * assadearv;
                            }
                        }
                    }
                    if (diilerivaartus < 17) {
                        valik2 = randomint.nextInt(kaardipakk.size());
                        diiler.add(kaardipakk.get(valik2));
                        kaardipakk.remove(valik2);
                        diilerivaartus = 0;
                        for (int m = 0; m < diiler.size(); m++) {
                            diilerivaartus += diiler.get(m).getVaartus();
                        }
                    }
                    if (diilerivaartus > 21) {
                        System.out.println("Diileri Bust");
                        diileribust = true;
                        break;
                    } else {
                        diileristand = true;
                    }
                }
                if (mangijabust) {
                    if (diileribust) {
                        System.out.println("Viik");
                    } else {
                        System.out.println("Sina kaotasid");
                    }
                }
                if (!mangijabust) {
                    if (diileribust) {
                        System.out.println("OFOOFOSinu võit");
                    } else {
                        System.out.println("Su vaartus: " + mangijavaartus);
                        System.out.println("Diileri vaartus" + diilerivaartus);
                        if (mangijavaartus > diilerivaartus) {
                            System.out.println("Sina voitsid");
                        } else {
                            System.out.println("Sina kaotasid");
                        }

                    }
                }


            }
        }
        return voidud;
    }

}
