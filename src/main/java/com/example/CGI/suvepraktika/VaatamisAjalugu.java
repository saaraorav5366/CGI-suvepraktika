package com.example.CGI.suvepraktika;
import java.util.List;
import java.util.Set;
/**
 * Klass nimega VaatamisaAjalugu, mis esindab kasutaja vaatamis ajaloo atribuute ja käitumist.
 */
public class VaatamisAjalugu {
    /**
     *  VaatamisAjaloo üheks objektiks on kasutajaId, mis on esitatud Kasutajana.
     */
    private Kasutaja kasutajaId;
    /**
     *  VaatamisAjaloo teiseks objektiks on vaadatudFilmid, mis on esitatud Set<List<String>>.
     */
    private Set<List<String>> vaadatudFilmid;

    /**
     * VaatamisAjaloo konstruktor.
     * @param kasutajaId kasutaja ID
     * @param vaadatudFilmid filmid mida kasutajaId on vaatanud
     */
    public VaatamisAjalugu(Kasutaja kasutajaId, Set<List<String>> vaadatudFilmid) {
        this.kasutajaId = kasutajaId;
        this.vaadatudFilmid = vaadatudFilmid;
    }

    /**
     *  Getter meetodid kasutajaId ja vaadatudFilmid kasutamiseks.
     */
    public Kasutaja getKasutajaId() {
        return kasutajaId;
    }

    public Set<List<String>> getVaadatudFilmid() {
        return vaadatudFilmid;
    }

}

