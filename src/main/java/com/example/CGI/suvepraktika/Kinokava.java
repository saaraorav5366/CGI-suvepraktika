package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Kinokava {

    public List<Seanss> genereeriKinokava() {
        List<Seanss> kinokava = new ArrayList<>();
        kinokava.add(new Seanss(new Film("Oppenheimer", "Ponevik", 16, Arrays.asList("Inglise keel", "Vene keel")), Arrays.asList(13.00, 15.35, 18.15, 22.00)));
        kinokava.add(new Seanss(new Film("Barbie", "Komoodia", 12, List.of("Inglise keel")), Arrays.asList(12.00, 14.30, 18.55, 20.00)));
        kinokava.add(new Seanss(new Film("Kung Fu Panda 4", "Seiklusfilm", 4, List.of("Eesti keel")), Arrays.asList(11.00, 14.30, 18.00)));
        kinokava.add(new Seanss(new Film("The Greatest Showman", "Muusikal", 10, List.of("Inglise keel")), Arrays.asList(16.05, 19.00)));
        kinokava.add(new Seanss(new Film("Spider-Man: No Way Home", "Marul", 12, List.of("Inglise keel")), Arrays.asList(12.10, 18.55, 20.00, 22.30)));
        kinokava.add(new Seanss(new Film("The Sound of Music", "Muusikal", 10, List.of("Inglise keel")), Arrays.asList(19.30, 21.00)));
        kinokava.add(new Seanss(new Film("Poor Things", "Draama", 14, List.of("Inglise keel")), Arrays.asList(16.35, 19.30, 22.00)));
        kinokava.add(new Seanss(new Film("Don't Worry Darling", "Draama", 16, List.of("Inglise keel")), Arrays.asList(18.00, 21.00)));
        return kinokava;
    }



    public Set<Seanss> getKinokava(Integer vanusepiirang, String keel, String zanr, Double algusaeg) {
        Set<Seanss> filtreeritudKava = new HashSet<>();
        List<Seanss> koguKava = genereeriKinokava();

        for (Seanss seanss : koguKava) {
            Film film = seanss.getFilm();
            if ((vanusepiirang == null || vanusepiirang <= film.getVanusepiirang()) &&
                    (keel == null || film.getKeel().contains(keel)) &&
                    (zanr == null || Objects.equals(film.getZanr(), zanr))) {
                for (Double aeg : seanss.getAlgusaeg()) {
                    if (algusaeg == null || algusaeg <= aeg) {
                        filtreeritudKava.add(seanss);
                        break;
                    }
                }
            }
        }
        return filtreeritudKava;
    }

}
