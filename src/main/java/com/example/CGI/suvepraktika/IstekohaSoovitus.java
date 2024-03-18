package com.example.CGI.suvepraktika;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class IstekohaSoovitus {


    public int[][] genereeriSaal(int laius, int korgus, int soovitudKohad) {
        int maxKohad = laius*korgus;
        int[][] saal = new int[laius][korgus];
        Random random = new Random();
        // vahemalt 5 kohta on igas saalis tuhi (lisaks soovitud kohtadele)
        int voetudKohad = random.nextInt((maxKohad)- 5 - soovitudKohad);

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

        int keskmineRida = veerg / 2;
        int keskmineVeerg = rida / 2;

        // alusta soovitamist keskmisest reast ja veerust
        int j
        for (int k = 0; k <= keskmineRida; k++) {
            int i = keskmineRida + k * (k % 2 == 0 ? 1 : -1);
            if (i < 0 || i >= veerg) continue;
            for (j = keskmineVeerg; j <= rida; j++) {
                if (saal[i][j] == 2) {
                    for(int m = 0)
                }
                if (saal[i][j] == 3) {

                }

            for (j = keskmineVeerg; j <= 0; j--) {
                if (saal[i][l] == 2) {

                }
                if (saal[i][l] == 3) {

                }
            }
        }
        return saal;
    }

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
