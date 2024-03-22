package com.example.CGI.suvepraktika;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * Klass nimega KinoController, mis pakub RESTful teenuseid.
 */
@RestController
public class KinoController {

    /**
     * Kinokava objekt, mis võimaldab pääseda kinos olevate seansside andmetele
     */
    private final Kinokava kava;

    /**
     * IstekohaSoovitus objekt, mis aitab soovitada istekohti kinosaalis
     */
    private final IstekohaSoovitus kohaSoovitus;

    /**
     * KinoController klassi konstruktor.
     * @param kava hetkene kinokava
     * @param kohaSoovitus IstekohaSoovitus objekt
     */
    public KinoController(Kinokava kava, IstekohaSoovitus kohaSoovitus) {
        this.kava = kava;
        this.kohaSoovitus = kohaSoovitus;
    }

    /**
     * GetMapping annotatsioon määrab, et meetod reageerib GET päringutele /kava teekonnal ja tagastab kinokava.
     * Filtrite hulka kuuluvad: vanusepiirang, filmi keel, filmi žanr ja filmi algusaeg. Filtreid võib rakendada, aga ei pea.
     */
    @GetMapping("/kava")
    public Set<Seanss> getKinokava(@RequestParam(required = false) Integer vanusepiirang,
                                   @RequestParam(required = false) String keel,
                                   @RequestParam(required = false) String zanr,
                                   @RequestParam(required = false) Double algusaeg) {
        return kava.getKinokava(vanusepiirang, keel, zanr, algusaeg);
    }

    /**
     * GetMapping annotatsioon määrab, et meetod reageerib GET päringutele /filmisoovitused teekonnal ja tagastab filmi soovitused kasutajale.
     * Ainukeseks tingimuseks on see, et peab sisestama olemasoleva kasutajaId. Nt. kasutajaId = 90225
     */
    @GetMapping("/filmisoovitused")
    public Set<Seanss> getSoovitused(@RequestParam Integer kasutajaId, @RequestParam(required = false) Integer vanusepiirang, @RequestParam(required = false) String keel, @RequestParam(required = false) String zanr, @RequestParam(required = false)Double algusaeg) {
        // genereeri vaatajad ja leia soovitud kasutaja vaatamisajalugu
        List<VaatamisAjalugu> vaatajad = VaatamisteGenereerija.genereeriVaatajad();
        VaatamisAjalugu viewer = null;
        // leia oige kasutajaId / vaataja
        for (VaatamisAjalugu vaataja : vaatajad) {
            if (Objects.equals(vaataja.getKasutajaId().getKasutajaId(), kasutajaId)) {
                viewer = vaataja;
                break;
            }
        }
        // leia filmi soovitused vastavalt kasutaja eelistustele
        Set<Seanss> soovitus = kava.getKinokava(vanusepiirang, keel,  zanr, algusaeg);
        assert viewer != null;
        return VaatamisSoovitused.genereeriFilmisoovitused(viewer, soovitus);
    }

    /**
     * GetMapping annotatsioon määrab, et meetod reageerib GET päringutele /kohad teekonnal ja tagastab soovitatud istekohad.
     * Ainukeseks tingimuseks on see, et peab sisestama soovitud piletite arvu.
     */
    @GetMapping("/kohad")
    public int[][] getSaal(int soovitudKohad) {

        // genereeri kinosaal, leia soovitatud järjestikused kohad ja anna soovitus
        // otsustasin kino suuruseks valida 14x12
        int[][] saal = kohaSoovitus.genereeriSaal(14,12, soovitudKohad);
        int[][] saal2 = kohaSoovitus.leiaJarjestKohad(saal,soovitudKohad);
        return kohaSoovitus.soovitaKohad(saal2, soovitudKohad);
    }

}

