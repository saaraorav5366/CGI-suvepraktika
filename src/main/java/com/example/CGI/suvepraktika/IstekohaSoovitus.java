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
        // vahemalt 10 kohta on igas saalis tuhi (lisaks soovitud kohtadele)
        int voetudKohad = random.nextInt((maxKohad)- 10 - soovitudKohad);

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

}
