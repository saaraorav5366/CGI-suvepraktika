
package com.example.CGI.suvepraktika;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/kino")
public class KinoController {
    private final Kinokava kava;

    public KinoController(Kinokava kava) {
        this.kava = kava;
    }


    @GetMapping("/kava")
    public List<Seanss> getKinokava(@RequestParam(required = false) Integer vanusepiirang,
                                    @RequestParam(required = false) String keel,
                                    @RequestParam(required = false) String zanr,
                                    @RequestParam(required = false) Double algusaeg) {
        return kava.getKinokava(vanusepiirang, keel, zanr, algusaeg);
    }
}

