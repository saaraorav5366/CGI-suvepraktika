package com.example.CGI.suvepraktika;

/**
 * Klass nimega kasutaja, mis esindab kinokasutaja atribuute ja käitumist.
 */
public class Kasutaja {

    /**
     *  Kasutaja id on esitatud täisarvuna.
     */
    private final Integer kasutajaId;

    /**
     * Kasutaja konstruktor.
     * @param kasutajaId kasutaja id
     */
    public Kasutaja(Integer kasutajaId){
        this.kasutajaId = kasutajaId;
    }

    /**
     *  Getter meetod kasutaja id kasutamiseks.
     */
    public Integer getKasutajaId(){
        return kasutajaId;
    }

}
