package com.example.CGI.suvepraktika;

import java.util.List;

/**
 * Klass nimega Seanss, mis esindab kinofilmi ja algusaja atribuute ja käitumist.
 */
public class Seanss {

    /**
     * Filmi object mis vastab seansile.
     */
    private Film film;

    /**
     * Filmi algusaeg on esitatud List<Double>, sest filmilmidel on tavaliselt mitu algusaega
     * ja et algusaja filtreerimist võimalikult lihtsaks teha.
     */
    private List<Double> algusaeg;

    /**
     * Seanss klassi konstruktor.
     * @param film object mis vastab seansile.
     * @param algusaeg filmi algusaeg
     */
    public Seanss(Film film, List<Double> algusaeg) {
        this.film = film;
        this.algusaeg = algusaeg;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Double> getAlgusaeg() {
        return algusaeg;
    }

    public void setAlgusaeg(List<Double> algusaeg) {
        this.algusaeg = algusaeg;
    }
}
