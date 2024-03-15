package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class VaatamisSoovitused {

    public static Set<Seanss> genereeriFilmisoovitused(VaatamisAjalugu vaatamisAjalugu, Set<Seanss> kinokava){

        Set<List<String>> vaadatudFilmid = vaatamisAjalugu.getVaadatudFilmid();

        // kui vaatajal pole vaatamisajalugu -- soovita suvaliselt 3 filmi
        if (vaadatudFilmid.isEmpty()) {
            Set<Seanss> set = new HashSet<>();
            set.add(kinokava.stream().skip(new Random().nextInt(kinokava.size())).findFirst().orElse(null)); //INTERNET!!
            return set;
        }

        // kasutan HashMap'i et loendada vaataja zanre ja keeli
        Map<String, Integer> zanrArv = new HashMap<>();
        Map<String, Integer> keelteArv = new HashMap<>();
        for (List<String> filmInfo : vaadatudFilmid) {
            String zanr = filmInfo.get(1); // zanr asub index 1
            String keel = filmInfo.get(2); // filmi keel asub index 2
            zanrArv.put(zanr, zanrArv.getOrDefault(zanr, 0) + 1);
            keelteArv.put(keel, keelteArv.getOrDefault(keel, 0) + 1);
        }

        // leia enimvaadatud zanr ja keel
        String vaadatuimZanr = Collections.max(zanrArv.entrySet(), Map.Entry.comparingByValue()).getKey();
        String vaadatuimKeel = Collections.max(keelteArv.entrySet(), Map.Entry.comparingByValue()).getKey();

        // genereeri filmisoovitused
        Set<Seanss> soovitused = new HashSet<>();
        for (Seanss seanss : kinokava) {
            Film film = seanss.getFilm();
            if (film.getZanr().equals(vaadatuimZanr) && !vaadatudFilmid.contains(Arrays.asList(film.getPealkiri(), vaadatuimZanr, vaadatuimKeel))) {
                soovitused.add(seanss);
            }
        }

        // kui juba enimvaadatud zanri film on nähtud
//        if (recommendations.isEmpty()) {
//
//        }

        return soovitused;
    }
}
