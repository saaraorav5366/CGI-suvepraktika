package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * Klass nimega Kinokava, mis esindab hetke kinokava atribuute ja käitumist.
 */
@Component
public class Kinokava {

    /**
     * Meetod, mis genereerib hetkese kinokava.
     * @return kinokava, mis on esitatud List<Seanss> kujul.
     */
    public List<Seanss> genereeriKinokava() {
        List<Seanss> kinokava = new ArrayList<>();
        kinokava.add(new Seanss(new Film("Oppenheimer", "Ponevik", 16, "Inglise keel"), Arrays.asList(13.00, 15.35, 18.15, 22.00)));
        kinokava.add(new Seanss(new Film("Oppenheimer", "Ponevik", 16, "Vene keel"), Arrays.asList( 18.35, 22.30)));
        kinokava.add(new Seanss(new Film("Barbie", "Komoodia", 12, "Inglise keel"), Arrays.asList(12.00, 14.30, 18.55, 20.00)));
        kinokava.add(new Seanss(new Film("Kung Fu Panda 4", "Seiklusfilm", 4,"Eesti keel"), Arrays.asList(11.00, 14.30, 18.00)));
        kinokava.add(new Seanss(new Film("The Greatest Showman", "Muusikal", 10, "Inglise keel"), Arrays.asList(16.05, 19.00)));
        kinokava.add(new Seanss(new Film("Spider-Man: No Way Home", "Marul", 12, "Inglise keel"), Arrays.asList(12.10, 18.55, 20.00, 22.30)));
        kinokava.add(new Seanss(new Film("The Sound of Music", "Muusikal", 10, "Inglise keel"), Arrays.asList(19.30, 21.00)));
        kinokava.add(new Seanss(new Film("Poor Things", "Draama", 14, "Inglise keel"), Arrays.asList(16.35, 19.30, 22.00)));
        kinokava.add(new Seanss(new Film("Don't Worry Darling", "Draama", 16, "Inglise keel"), Arrays.asList(18.00, 21.00)));
        return kinokava;
    }

    /**
     * Meetod, mis filtreerib kinokava vastavalt filtrile.
     *
     * @param vanusepiirang täisarv mis määrab kui vanad inimesed võivad filmi vaadata
     * @param keel string mis esindab filmi keelt
     * @param zanr string mis esindab filmi žanri
     * @param algusaeg double mis määrab filmi algusaega
     * @return kinokava, mis on esitatud Set<Seanss> kujul, et poleks kordusi.
     */
    public Set<Seanss> getKinokava(Integer vanusepiirang, String keel, String zanr, Double algusaeg) {
        Set<Seanss> filtreeritudKava = new HashSet<>(); // loo uus Set, mis lõpuks tagastatakse
        List<Seanss> koguKava = genereeriKinokava();
        // iterate labi iga seansi, et testida kas filter on kaivitatud
        for (Seanss seanss : koguKava) {
            Film film = seanss.getFilm();
            if ((vanusepiirang == null || vanusepiirang <= film.getVanusepiirang()) && // kui vanusepiirangu filter on nt. 12, siis tagastatakse filmid, mille vanusepiirang on 12 voi vanem
                    (keel == null || film.getKeel().contains(keel)) &&
                    (zanr == null || Objects.equals(film.getZanr(), zanr))) {
                for (Double aeg : seanss.getAlgusaeg()) { // iterate labi iga algusaja ja testi filtrit
                    if (algusaeg == null || algusaeg <= aeg) {
                        filtreeritudKava.add(seanss); // lisa seanss filtreeritud kavasse ainult siis, kui seanss vastab filtri tingimustele
                        break;
                    }
                }
            }
        }
        return filtreeritudKava;
    }

}
