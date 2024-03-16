package com.example.CGI.suvepraktika;

public class Istekoht {
    private final int rida;
    private final int veerg;

    public Istekoht(int rida, int veerg){
        this.rida = rida;
        this.veerg = veerg;
    }

    public int getRida() {
        return rida;
    }

    public int getVeerg() {
        return veerg;
    }
}
