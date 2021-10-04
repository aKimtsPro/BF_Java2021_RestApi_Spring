package bstorm.akimts.restapi.controller;

import bstorm.akimts.restapi.exceptions.models.VoitureNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/first")
    public void hello(){
        log.info("On passe par ici");
    }

    @GetMapping
    public void getHeader(@RequestHeader HttpHeaders headers){
        headers.keySet().forEach(log::warn);
    }

    @GetMapping("/ill_arg")
    public void throwIllegalArgument(){
        throw new IllegalArgumentException("lancé volontairement");
    }

    @GetMapping("/voit_not_found")
    public void throwVoitureNotFound(){
        throw new VoitureNotFoundException(-1);
    }

    @GetMapping("/not_handled")
    public void throwUnhandled(){
        throw new RuntimeException();
    }


}
