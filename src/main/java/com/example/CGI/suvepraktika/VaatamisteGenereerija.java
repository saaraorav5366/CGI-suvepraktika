package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Klass nimega Kinokava, mis genereerib vaatamised
 */
@Component
public class VaatamisteGenereerija {

        /**
         * Meetod, mis genereerib kasutaja ja tema vaatamis ajaloo.
         * @return vaataja vaatamis ajaloo, mis on esitatud List<VaatamisAjalugu> kujul.
         */
        public static List<VaatamisAjalugu> genereeriVaatajad(){
        List<VaatamisAjalugu> vaatajad = new ArrayList<>();
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90220), new HashSet<>(new ArrayList<>()))); //vaatamisajalugu puudub
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90225),
                Set.of(List.of("Oppenheimer","Ponevik", "Inglise keel"),
                List.of("Barbie","Komoodia", "Inglise keel"),           // algoritm soovitab: Spider-Man: No Way Home
                List.of("Step Brothers","Komoodia", "Inglise keel"),
                List.of("The Matrix","Ponevik", "Inglise keel"),
                List.of("John Wick","Ponevik", "Inglise keel"))));
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90230),
                Set.of(List.of("Oppenheimer","Ponevik", "Inglise keel"), // on ühte ja samat filmi kahes erinevas keeles näinud
                        List.of("Oppenheimer","Ponevik", "Eesti keel"), // alogritm soovitab: Don't Worry Darling
                        List.of("Klass","Draama", "Eesti keel"),
                        List.of("Kevade","Draama", "Eesti keel"),
                        List.of("Poor Things","Draama", "Inglise keel"))));
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90235), // alogritm soovitab: Don't Worry Darling ja Poor Things
                Set.of(List.of("Klass","Draama", "Eesti keel"))));
        return vaatajad;

    }
}
