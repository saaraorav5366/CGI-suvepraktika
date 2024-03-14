package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Kinokava {

    public List<Seanss> genereeriKinokava() {
        List<Seanss> kinokava = new ArrayList<>();
        kinokava.add(new Seanss(new Film("Oppenheimer", "Ponevik", 16, Arrays.asList("Inglise keel", "Vene keel")), Arrays.asList(13.00, 15.35, 18.15, 22.00)));
        kinokava.add(new Seanss(new Film("Barbie", "Komoodia", 12, Arrays.asList("Inglise keel")), Arrays.asList(12.00, 14.30, 18.55, 20.00)));
        kinokava.add(new Seanss(new Film("Kung Fu Panda 4", "Seiklusfilm", 4, Arrays.asList("Eesti keel")), Arrays.asList(11.00, 14.30, 18.00)));
        kinokava.add(new Seanss(new Film("The Greatest Showman", "Muusikal", 10, Arrays.asList("Inglise keel")), Arrays.asList(16.05, 19.00)));
        kinokava.add(new Seanss(new Film("Spider-Man: No Way Home", "Marul", 12, Arrays.asList("Inglise keel")), Arrays.asList(12.10, 18.55, 20.00, 22.30)));
        return kinokava;
    }


    public List<Seanss> getKinokava(Integer vanusepiirang, String keel, String zanr, Double algusaeg) {
        List<Seanss> filtreeritudKava = new ArrayList<>();
        List<Seanss> koguKava = genereeriKinokava();
        List<String> zanriValikud = Arrays.asList("Ponevik", "Komoodia", "Seikludfilm", "Muusikal", "Marul");
        for (Seanss seanss : koguKava) {
            Film film = seanss.getFilm();
            if ((vanusepiirang == null || vanusepiirang <= film.getVanusepiirang()) &&
                    (keel == null || film.getKeel().contains(keel)) &&
                    (zanr == null || zanriValikud.contains(film.getZanr()))){
                filtreeritudKava.add(seanss);
            }
            for(Double aeg : seanss.getAlgusaeg()){
                if(algusaeg == null || algusaeg <= aeg){
                    filtreeritudKava.add(seanss);
                }
            }
        }
        return filtreeritudKava;
    }
}
