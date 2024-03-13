package com.example.CGI.suvepraktika;

import java.time.LocalDateTime;

public class Seanss {
    private Film film;
    private String algusAeg;

    public Seanss(Film film, String algusAeg) {
        this.film = film;
        this.algusAeg = algusAeg;
    }

    // Getters and setters
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getAlgusAeg() {
        return algusAeg;
    }

    public void setAlgusAeg(String algusAeg) {
        this.algusAeg = algusAeg;
    }
}
