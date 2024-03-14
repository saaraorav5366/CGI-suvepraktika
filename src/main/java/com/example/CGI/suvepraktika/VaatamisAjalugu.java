package com.example.CGI.suvepraktika;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class VaatamisAjalugu {
    private Integer kasutajaId;
    private Set<List<String>> vaadatudFilmid;

    public VaatamisAjalugu(Integer kasutajaId, Set<List<String>> vaadatudFilmid) {
        this.kasutajaId = kasutajaId;
        this.vaadatudFilmid = vaadatudFilmid;
    }

    public void setKasutajaId(Integer kasutajaId) {
        this.kasutajaId = kasutajaId;
    }

    public Integer getKasutajaId() {
        return kasutajaId;
    }

    public void setVaadatudFilmid(Set<List<String>> vaadatudFilmid) {
        this.vaadatudFilmid = vaadatudFilmid;
    }
    public Set<List<String>> getVaadatudFilmid() {
        return vaadatudFilmid;
    }

    public List<VaatamisAjalugu> genereeriVaatajad(){
        List<VaatamisAjalugu> vaatajad = new ArrayList<>();
        vaatajad.add(new VaatamisAjalugu(90220, Set.of(List.of()))); //vaatamisajalugu puudub
        vaatajad.add(new VaatamisAjalugu(90225,
                Set.of(List.of("Oppenheimer","Ponevik", "Inglise keel"), // on kõige rohkem põnevikke näinud
                List.of("Barbie","Komoodia", "Inglise keel"),           // algoritm peaks soovitama: Spider-Man: No Way Home
                List.of("Step Brothers","Komoodia", "Inglise keel"),
                List.of("The Matrix","Ponevik", "Inglise keel"),
                List.of("John Wick","Ponevik", "Inglise keel"))));
        vaatajad.add(new VaatamisAjalugu(90230,
                Set.of(List.of("Oppenheimer","Ponevik", "Inglise keel"), // on ühte ja samat filmi kahes erinevas keeles näinud
                        List.of("Oppenheimer","Ponevik", "Eesti keel"), // alogritm peaks soovitama: Don't Worry Darling
                        List.of("Klass","Draama", "Eesti keel"),
                        List.of("Kevade","Draama", "Eesti keel"),
                        List.of("Poor Things","Drama", "Inglise keel"))));
        vaatajad.add(new VaatamisAjalugu(90235,
                Set.of(List.of("Klass","Draama", "Eesti keel")))); // alogritm peaks soovitama: Don't Worry Darling ja Poor Things
        return vaatajad;
    }
}
