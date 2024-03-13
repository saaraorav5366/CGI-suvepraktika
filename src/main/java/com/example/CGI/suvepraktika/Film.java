package com.example.CGI.suvepraktika;

public class Film {
    private String pealkiri;
    private String žanr;
    private Integer vanusepiirang;
    private String keel;

    // Constructor
    public Film(String pealkiri, String žanr, Integer vanusepiirang, String keel) {
        this.pealkiri = pealkiri;
        this.žanr = žanr;
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

    public String getŽanr() {
        return žanr;
    }

    public void setŽanr(String žanr) {
        this.žanr = žanr;
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
