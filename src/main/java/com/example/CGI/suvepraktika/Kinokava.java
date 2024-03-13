package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
public class Kinokava {

    public List<Seanss> getKinokava() {
        List<Seanss> kinokava = new ArrayList<>();
        kinokava.add(new Seanss(new Film("Oppenheimer", "Thriller", 14, "Inglise keel"), "10:00"));
        return kinokava;
    }
}
