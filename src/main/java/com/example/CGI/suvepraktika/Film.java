package com.example.CGI.suvepraktika;
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
     *  Filmi keel on esitatud String.
     */
    private String keel;

    /**
     * Filmi konstruktor.
     * @param pealkiri filmi pealkiri
     * @param zanr filmi žanr
     * @param vanusepiirang filmi vanusepiirang
     * @param keel filmi keel
     */
    public Film(String pealkiri, String zanr, Integer vanusepiirang, String keel) {
        this.pealkiri = pealkiri;
        this.zanr = zanr;
        this.vanusepiirang = vanusepiirang;
        this.keel = keel;
    }

    /**
     *  Getter meetodid pealkirja, zanri, vanusepiirangu ja keele kasutamiseks.
     */
    public String getPealkiri() {
        return pealkiri;
    }

    public String getZanr() {
        return zanr;
    }

    public Integer getVanusepiirang() {
        return vanusepiirang;
    }

    public String getKeel() {
        return keel;
    }


}
