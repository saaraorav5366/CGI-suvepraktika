package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Kinokava {

    public List<Seanss> genereeriKinokava() {
        List<Seanss> kinokava = new ArrayList<>();
        kinokava.add(new Seanss(new Film("Oppenheimer", "Thriller", 14, "Inglise keel"), "10:00"));
        kinokava.add(new Seanss(new Film("Barbie", "Family", 10, "Inglise keel"), "12:00"));
        return kinokava;
    }


    public List<Seanss> getKinokava(Integer vanusepiirang, String keel, String zanr, String algusaeg) {
        List<Seanss> filtreeritudKava = new ArrayList<>();
        List<Seanss> koguKava = genereeriKinokava();
        for (Seanss seanss : koguKava) {
            Film film = seanss.getFilm();

            if ((vanusepiirang == null || vanusepiirang <= film.getVanusepiirang() )) {
                filtreeritudKava.add(seanss);
            }
        }
        return filtreeritudKava;
    }
}
