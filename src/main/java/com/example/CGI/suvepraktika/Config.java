package com.example.CGI.suvepraktika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class Config {
        public static List<VaatamisAjalugu> genereeriVaatajad(){
        List<VaatamisAjalugu> vaatajad = new ArrayList<>();
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90220), new HashSet<>(new ArrayList<>()))); //vaatamisajalugu puudub
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90225),
                Set.of(List.of("Oppenheimer","Ponevik", "Inglise keel"), // on kõige rohkem põnevikke näinud
                List.of("Barbie","Komoodia", "Inglise keel"),           // algoritm peaks soovitama: Spider-Man: No Way Home
                List.of("Step Brothers","Komoodia", "Inglise keel"),
                List.of("The Matrix","Ponevik", "Inglise keel"),
                List.of("John Wick","Ponevik", "Inglise keel"))));
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90230),
                Set.of(List.of("Oppenheimer","Ponevik", "Inglise keel"), // on ühte ja samat filmi kahes erinevas keeles näinud
                        List.of("Oppenheimer","Ponevik", "Eesti keel"), // alogritm peaks soovitama: Don't Worry Darling
                        List.of("Klass","Draama", "Eesti keel"),
                        List.of("Kevade","Draama", "Eesti keel"),
                        List.of("Poor Things","Draama", "Inglise keel"))));
        vaatajad.add(new VaatamisAjalugu(new Kasutaja(90235), // alogritm peaks soovitama: Don't Worry Darling ja Poor Things
                Set.of(List.of("Klass","Draama", "Eesti keel"))));
        return vaatajad;
    }
}
