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

    int[][] soovitaKohad(int[][] saal, int soovitudKohad) {

        int laius = saal[0].length;
        int korgus = saal.length;

        int keskmineRida = laius / 2;
        int keskmineVeerg = korgus / 2;

        int count = 0;
        // alusta soovitamist keskmisest reast ja veerust
        for (int k = 0; k <= keskmineRida; k++) {
            int rida = keskmineRida + k * (k % 2 == 0 ? 1 : -1);
            if (rida < 0 || rida >= laius) continue;
            for (int l = 0; l <= keskmineVeerg; l++) {
                int j = keskmineVeerg + l * (l % 2 == 0 ? 1 : -1);
                if (j < 0 || j >= korgus) continue;
                if (saal[rida][j] == 0) {
                    saal[rida][j] = 3;
                    count += 1;
                    if (count == soovitudKohad) {
                        break;
                    }
                }
            }
            if (count == soovitudKohad) {
                break;
            }
        }
        return saal;
    }


}
