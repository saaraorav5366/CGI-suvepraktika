
package com.example.CGI.suvepraktika;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/kino")
public class KinoController {
    private final Kinokava kava;

    public KinoController(Kinokava kava) {
        this.kava = kava;
    }


    @GetMapping("/kava")
    public Set<Seanss> getKinokava(@RequestParam(required = false) Integer vanusepiirang,
                                   @RequestParam(required = false) String keel,
                                   @RequestParam(required = false) String zanr,
                                   @RequestParam(required = false) Double algusaeg) {
        return kava.getKinokava(vanusepiirang, keel, zanr, algusaeg);
    }

    @GetMapping("/soovitused")
    public Set<Seanss> getSoovitused(@RequestParam Integer kasutajaId, @RequestParam(required = false) Integer vanusepiirang, @RequestParam(required = false) String keel, @RequestParam(required = false) String zanr, @RequestParam(required = false)Double algusaeg) {
        List<VaatamisAjalugu> vaatajad = Config.genereeriVaatajad();
        VaatamisAjalugu viewer = null;
        for (VaatamisAjalugu vaataja : vaatajad) {
            if (Objects.equals(vaataja.getKasutajaId().getKasutajaId(), kasutajaId)) {
                viewer = vaataja;
                break;
            }
        }


        Set<Seanss> soovitus = kava.getKinokava(vanusepiirang, keel,  zanr, algusaeg);

        assert viewer != null;
        return VaatamisSoovitused.genereeriFilmisoovitused(viewer, soovitus);
    }
}

