package com.example.CGI.suvepraktika;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class IstekohaSoovitus {

    public List<Istekoht> genereeriPlaan(int laius, int korgus) {

        List<Istekoht> koikKohad = new ArrayList<>();
        Random random = new Random();
        //genereeri suvaliselt number 0 kuni maxkohad
        int maxKohad = laius*korgus;
        int voetudKohad = random.nextInt((maxKohad)- 10);


        // Randomly fill seats
        while (voetudKohad > 0) {
            int rida = random.nextInt(laius);
            int veerg = random.nextInt(korgus);
            Istekoht koht = new Istekoht(rida, veerg);
            koikKohad.add(koht);
            voetudKohad--;
        }

        return koikKohad;
    }
}
