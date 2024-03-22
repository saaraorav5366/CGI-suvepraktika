package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Klass nimega VaatamisSoovitused, mis soovitab kasuatajale filmi vastavalt tema vaatamis ajaloole.
 */
@Service
public class VaatamisSoovitused {

    /**
     * Meetod, mis genereerib vaatajale filmi soovitused vastavalt mis hetkel kinokavas on.
     * Kui kasutajal ei ole vaatamisajalugu, siis meetod soovitab suvaliselt kolme filmi mis on hetkel kinokavas.
     * Kui aga vaatajal on vaatamisajalugu, siis see meetod eelistab filmi soovituses esmalt kasutaja vaadatumaid žanri.
     * Kui kõik kinokava filmid vaadatumaist žanrist on vaadatud, siis see meetod soovitab filme kõige sarnasemast žanrist enim vaadatud žanrile.
     * See meetod teeb ka kindlaks, et see ei soovita filmi, mida kasutaja juba näinud on.
     *
     * @return filmi soovitused vastavalt vaatajale, mis on esitatud Set<Seanss> kujul, et vältida kordusi.
     */
    public static Set<Seanss> genereeriFilmisoovitused(VaatamisAjalugu vaatamisAjalugu, Set<Seanss> kinokava){

        Set<List<String>> vaadatudFilmid = vaatamisAjalugu.getVaadatudFilmid();

        // kui vaatajal pole vaatamisajalugu -> soovita suvaliselt 3 filmi
        if (vaadatudFilmid.isEmpty()) {
            // teen koopia kinokavast kasutades List tüüpi, et hiljem saaks .get meetodid kasutada
            List<Seanss> kinokavaList = new ArrayList<>(kinokava);
            Set<Seanss> suvalisedSoovitused = new HashSet<>();
            Random random = new Random();
            // suvaliselt vali 3 film kinokavast
            while (suvalisedSoovitused.size() < 3 && !kinokavaList.isEmpty()) {
                int randomIndex = random.nextInt(kinokavaList.size());
                Seanss randomSeanss = kinokavaList.get(randomIndex);
                suvalisedSoovitused.add(randomSeanss);
                kinokavaList.remove(randomIndex); // kustuta validtud seanss kinokavaList'ist, et seda uuesti valida ei saaks
            }
            return suvalisedSoovitused;
        }

        // kasutan HashMap'i et loendada mitu korda on kasutaja ühte žanri näinud
        Map<String, Integer> zanrArv = new HashMap<>();
        for (List<String> filmInfo : vaadatudFilmid) {
            String zanr = filmInfo.get(1); // zanr asub index 1
            zanrArv.put(zanr, zanrArv.getOrDefault(zanr, 0) + 1);
        }
        // leian enimvaadatud žanri
        String vaadatuimZanr = Collections.max(zanrArv.entrySet(), Map.Entry.comparingByValue()).getKey();

        // genereeri filmisoovitused kui vaatajal on vaatamisajalugu
        Set<Seanss> soovitused = new HashSet<>();
        for (Seanss seanss : kinokava) {
            Film film = seanss.getFilm();
            if (film.getZanr().equals(vaadatuimZanr)) { // vordle kas enimvaadatud źanr vastab filmi žanrile
                boolean leitud = false;
                for (List<String> filmInfo : vaadatudFilmid) {
                    String pealkiri = filmInfo.get(0);
                    if (Objects.equals(pealkiri, film.getPealkiri())) { // vordleb kas kasutaja on varem filmi näinud
                        leitud = true;
                        break;
                    }
                }
                // lisa film soovituste hulka ainult siis kui kasutaja pole seda varem näinud
                if (!leitud) {
                    soovitused.add(seanss);
                }
            }
        }

        // kui juba kõik enimvaadatud zanri film on nähtud
        if (soovitused.isEmpty()) {
            String sarnaseimZanr = sarnaseimZanr(vaadatuimZanr); // kasuta sarnaseimZanr meetodid määrata sarnaseim žanr
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

    /**
     * Meetod mis määrab igale žanrile kinokavas kõige sarnasema žanri.
     * @return žanri, mis on kõige sarnasem kasutaja vaadatuimale žanrile
     */
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
