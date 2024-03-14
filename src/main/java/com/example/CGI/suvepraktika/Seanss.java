package com.example.CGI.suvepraktika;

/**
 * Klass nimega Seanss, mis esindab kinofilmi ja algusaja atribuute ja käitumist.
 */
public class Seanss {

    /**
     * Filmi object mis vastab seansile.
     */
    private Film film;

    /**
     * Filmi algusaeg on esitatud Stringina.
     */
    private String algusaeg;

    /**
     * Seanss klassi konstruktor.
     * @param film object mis vastab seansile.
     * @param algusaeg filmi algusaeg
     */
    public Seanss(Film film, String algusaeg) {
        this.film = film;
        this.algusaeg = algusaeg;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getAlgusaeg() {
        return algusaeg;
    }

    public void setAlgusaeg(String algusaeg) {
        this.algusaeg = algusaeg;
    }
}
