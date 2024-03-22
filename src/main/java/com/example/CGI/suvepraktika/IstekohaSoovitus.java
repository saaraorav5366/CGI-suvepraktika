package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Service;
import java.util.Random;
/**
 * Klass nimega IstekohaSoovitus, mis soovitab kasutajale istekoha vastavalt
 * vabadele kohtadele saalis ja mitu istekohta soovitakse.
 */
@Service
public class IstekohaSoovitus {

    /**
     * Meetod, mis suvaliselt täidab saali suvalise arvu võetud istekohtadega.
     * @return kinosaal mis on suvaliste võetud istekohatedga täidetud.
     */
    public int[][] genereeriSaal(int laius, int korgus, int soovitudKohad) {
        // arvuta saali suurus
        int maxKohad = laius*korgus;
        int[][] saal = new int[laius][korgus];
        Random random = new Random();
        // vahemalt 5 kohta on igas saalis tuhi (lisaks soovitud kohtadele)
        int voetudKohad = random.nextInt((maxKohad)- 5 - soovitudKohad);
        // suvaliselt täida kino võetud istekohtadega
        while (voetudKohad > 0) {
            int rida = random.nextInt(laius);
            int veerg = random.nextInt(korgus);
            if (saal[rida][veerg] == 0) {
                saal[rida][veerg] = 1;
                voetudKohad--;
            }
        }
        return saal;
    }

    public int[][] soovitaKohad(int[][] saal, int soovitudKohad) {

        int veerg = saal[0].length;
        int rida = saal.length;
        int keskmineRida = rida / 2;
        int keskmineVeerg = veerg / 2;

        // alusta soovitamist keskmisest reast ja veerust
        int huppecount = 1;
        boolean ulemine = false;
        int loend = 0;
        int k = keskmineRida;
        int j;
        while (k >= 0 && k < rida) {
            // alustad rea keskelt ja lahed paremale
            for (j = keskmineVeerg; j < veerg; j++) {
                //kui kohtad num 2 siis tahad minna paremale
                if (saal[k][j] == 2) {
                    for (int l = j; l < Math.min(j + soovitudKohad, veerg); l++) {
                        saal[k][l] = 4;
                    }
                    return saal;
                    //kui kohtad num 3 siis tahad minna vasakule
                } else if (saal[k][j] == 3) {
                    for (int l = j; l > Math.max(j - soovitudKohad, -1); l--) {
                        saal[k][l] = 4;
                    }
                    return saal;
                }
            }
            //alustad rea keskelt ja lahed vasakule
            for (j = keskmineVeerg - 1; j >= 0; j--) {
                if (saal[k][j] == 2) {
                    for (int l = j; l < Math.min(j + soovitudKohad, veerg); l++) {
                        saal[k][l] = 4;
                    }
                    return saal;
                } else if (saal[k][j] == 3) {
                    for (int l = j; l > Math.max(j - soovitudKohad, -1); l--) {
                        saal[k][l] = 4;
                    }
                    return saal;
                }
            }
            if (!ulemine) {
                k = k - huppecount;
                ulemine = true;
                huppecount += 1;
            } else {
                k = k + huppecount;
                ulemine = false;
                huppecount += 1;
            }
        }


        return piisavaltKohti(saal, soovitudKohad);
    }

    public int[][] piisavaltKohti(int[][] saal, int soovitudKohad){
        int pooledKohad;
        if(soovitudKohad % 2 == 0){
            pooledKohad = (soovitudKohad / 2);
        }
        else{
            pooledKohad = (soovitudKohad / 2) + 1; //kui soovitudKohad on paaritu arv siis umarda ules
        }
        int ulejaanudKohad = soovitudKohad - pooledKohad;
        int[][] jarjestKohad = leiaJarjestKohad(saal, pooledKohad);
        int[][] poolSaal = soovitaKohad(jarjestKohad, pooledKohad);
        return soovitaKohad(poolSaal, ulejaanudKohad);

    }

    /**
     * Meetod mis kontrollib, kas saalis on piisavalt järjestikke kohti soovitud kohtade jaoks. (Kas mitu piletit saab kõrvuti istuda)
     * @return kinosaal, mis on esitatud int[][] kujul.
     */
    public int[][] leiaJarjestKohad(int[][] saal, int soovitudKohad) {
        int rida = saal.length;
        int veerg = saal[0].length;
        for (int i = 0; i < rida; i++) {
            int count = 0;
            for (int j = 0; j < veerg; j++) {
                if (saal[i][j] == 0) {
                    count++;
                    if (count == soovitudKohad) {
                        saal[i][j - soovitudKohad + 1] = 2;
                        saal[i][j] = 3;
                        count = 0; // Reset count
                    }
                } else {
                    count = 0; // Reset count if a seat is occupied
                }
            }
        }
        return saal;
    }
}

