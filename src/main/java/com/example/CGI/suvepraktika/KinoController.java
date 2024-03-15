
package com.example.CGI.suvepraktika;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/kino")
public class KinoController {
    private final Kinokava kava;
//    private final VaatamisAjalugu vaatamisAjalugu;

    public KinoController(Kinokava kava) {
        this.kava = kava;
//        this.vaatamisAjalugu = vaatamisAjalugu;
    }


    @GetMapping("/kava")
    public Set<Seanss> getKinokava(@RequestParam(required = false) Integer vanusepiirang,
                                   @RequestParam(required = false) String keel,
                                   @RequestParam(required = false) String zanr,
                                   @RequestParam(required = false) Double algusaeg) {
        return kava.getKinokava(vanusepiirang, keel, zanr, algusaeg);
    }

    @GetMapping("/soovitused")
    public String getSoovitused(Integer kasutajaId, Integer vanusepiirang, String keel, String zanr, Double algusaeg) {
//        List<VaatamisAjalugu> vaatajad = vaatamisAjalugu.genereeriVaatajad();
//        VaatamisAjalugu viewer = null;
//        for (VaatamisAjalugu vaataja : vaatajad) {
//            if (vaataja.getKasutajaId() == kasutajaId) {
//                viewer = vaataja;
//                break;
//            }
//        }
//
//        if (viewer == null) {
//            return null;
//        }
//
//        Set<Seanss> filteredSchedule = kava.getKinokava(vanusepiirang, keel,  zanr, algusaeg);

        return "hello";
    }
}

