
package com.example.CGI.suvepraktika;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Seanss> getKinokava() {
        return kava.getKinokava();
    }
}

