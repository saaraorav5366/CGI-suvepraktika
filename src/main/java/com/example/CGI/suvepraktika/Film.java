package com.example.CGI.suvepraktika;

import java.util.List;

/**
 * Klass nimega Film, mis esindab kinofilmi atribuute ja käitumist.
 */
public class Film {
    /**
     *  Filmi pealkiri on esitatud Stringina.
     */
    private String pealkiri;
    /**
     *  Filmi žanr on esitatud Stringina.
     */
    private String zanr;

    /**
     *  Filmi vanusepiirang on esitatud täisarvuna, et teha filtreerimise rakendamist lihtsamaks.
     *  Oleks saanud rakendada ka Stringina nt: "Mitte alla 12a", "Perefilm".
     */
    private Integer vanusepiirang;

    /**
     *  Filmi keel on esitatud List<String>, sest filmi võidakse näidata mitmes keeles.
     */
    private List<String> keel;

    /**
     * Filmi konstruktor.
     * @param pealkiri filmi pealkiri
     * @param zanr filmi žanr
     * @param vanusepiirang filmi vanusepiirang
     * @param keel filmi keel
     */
    public Film(String pealkiri, String zanr, Integer vanusepiirang, List<String> keel) {
        this.pealkiri = pealkiri;
        this.zanr = zanr;
        this.vanusepiirang = vanusepiirang;
        this.keel = keel;
    }


    public void setPealkiri(String pealkiri) {
        this.pealkiri = pealkiri;
    }

    public String getPealkiri() {
        return pealkiri;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getZanr() {
        return zanr;
    }

    public void setVanusepiirang(Integer vanusepiirang) {
        this.vanusepiirang = vanusepiirang;
    }

    public Integer getVanusepiirang() {
        return vanusepiirang;
    }

    public void setKeel(List<String> keel) {
        this.keel = keel;
    }

    public List<String> getKeel() {
        return keel;
    }


}
