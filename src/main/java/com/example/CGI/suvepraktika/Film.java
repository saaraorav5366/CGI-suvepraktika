package com.example.CGI.suvepraktika;

public class Film {
    private String pealkiri;
    private String zanr;
    private Integer vanusepiirang;
    private String keel;

    // Constructor
    public Film(String pealkiri, String zanr, Integer vanusepiirang, String keel) {
        this.pealkiri = pealkiri;
        this.zanr = zanr;
        this.vanusepiirang = vanusepiirang;
        this.keel = keel;
    }

    // Getters and setters
    public String getPealkiri() {
        return pealkiri;
    }

    public void setPealkiri(String pealkiri) {
        this.pealkiri = pealkiri;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public Integer getVanusepiirang() {
        return vanusepiirang;
    }

    public void setVanusepiirang(Integer vanusepiirang) {
        this.vanusepiirang = vanusepiirang;
    }

    public String getKeel() {
        return keel;
    }

    public void setKeel(String keel) {
        this.keel = keel;
    }

}
