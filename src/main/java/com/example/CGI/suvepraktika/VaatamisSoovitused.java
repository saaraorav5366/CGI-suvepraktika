package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VaatamisSoovitused {

    public static String genereeriFilmisoovitused(VaatamisAjalugu vaatamisAjalugu, Set<Seanss> kinokava){

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
//            return set;
            return "hello";
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
        System.out.println((vaadatuimZanr));
        String vaadatuimKeel = Collections.max(keelteArv.entrySet(), Map.Entry.comparingByValue()).getKey();

        // genereeri filmisoovitused
        //algoritm tootab kasutajaga 90235
        Set<Seanss> soovitused = new HashSet<>();
        for (Seanss seanss : kinokava) {
            Film film = seanss.getFilm();
            if (film.getZanr().equals(vaadatuimZanr) && !vaadatudFilmid.contains(Arrays.asList(film.getPealkiri(), film.getZanr(), film.getKeel()))) {
                soovitused.add(seanss);
            }
        }

        // kui juba enimvaadatud zanri film on nähtud
//        if (soovitused.isEmpty()) {
//
//        }

        return vaadatuimZanr;
    }
}
