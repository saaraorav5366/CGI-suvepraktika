package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VaatamisSoovitused {

    public static Set<Seanss> genereeriFilmisoovitused(VaatamisAjalugu vaatamisAjalugu, Set<Seanss> kinokava){

        Set<List<String>> vaadatudFilmid = vaatamisAjalugu.getVaadatudFilmid();

        // kui vaatajal pole vaatamisajalugu -- soovita suvaliselt 3 filmi
        if (vaadatudFilmid.isEmpty()) {
            List<Seanss> kinokavaList = new ArrayList<>(kinokava); //koopia kinokavast kasutades List, et hiljem saaks get kasutada
            Set<Seanss> set = new HashSet<>();
            Random random = new Random();
            while (set.size() < 3 && !kinokavaList.isEmpty()) {
                int randomIndex = random.nextInt(kinokavaList.size());
                Seanss randomSeanss = kinokavaList.get(randomIndex);
                set.add(randomSeanss);
                kinokavaList.remove(randomIndex); // kustuta validtud seanss kinokavaList'ist
            }
            return set;
        }

        // kasutan HashMap'i et loendada vaataja zanre ja keeli
        Map<String, Integer> zanrArv = new HashMap<>();
        for (List<String> filmInfo : vaadatudFilmid) {
            String zanr = filmInfo.get(1); // zanr asub index 1
            zanrArv.put(zanr, zanrArv.getOrDefault(zanr, 0) + 1);
        }

        // leia enimvaadatud zanr
        String vaadatuimZanr = Collections.max(zanrArv.entrySet(), Map.Entry.comparingByValue()).getKey();

        // genereeri filmisoovitused
        //algoritm tootab kasutajaga 90235
        Set<Seanss> soovitused = new HashSet<>();
        for (Seanss seanss : kinokava) {
            Film film = seanss.getFilm();
            if (film.getZanr().equals(vaadatuimZanr)) {
                boolean leitud = false;
                for (List<String> filmInfo : vaadatudFilmid) {
                    String pealkiri = filmInfo.get(0);
                    if (Objects.equals(pealkiri, film.getPealkiri())) {
                        leitud = true;
                        break;
                    }
                }
                // kui pealkiri ei ole vaadatudFilmid's
                if (!leitud) {
                    soovitused.add(seanss);
                }
            }
        }

//         kui juba enimvaadatud zanri film on nähtud ja sellest zanrist pole muid filme
        if (soovitused.isEmpty()) {
            String sarnaseimZanr = sarnaseimZanr(vaadatuimZanr);

            // otsi filme millel on kõige sarnaseim zanr
            for (Seanss seanss : kinokava) {
                Film film = seanss.getFilm();
                if (film.getZanr().equals(sarnaseimZanr)) {
                    soovitused.add(seanss);
                }
            }
        }
        return soovitused;
    }

    private static String sarnaseimZanr(String vaadatuimZanr){

        if(Objects.equals(vaadatuimZanr, "Ponevik")){
            return "Marul";
        } else if (Objects.equals(vaadatuimZanr, "Marul")) {
            return "Ponevik";
        } else if (Objects.equals(vaadatuimZanr, "Muusikal")) {
            return "Draama";
        } else if (Objects.equals(vaadatuimZanr, "Draama")) {
            return "Muusikal";
        } else if (Objects.equals(vaadatuimZanr, "Seiklusfilm")) {
            return "Komoodia";
        } else {
            return "Seiklusfilm";
        }
    }
}
