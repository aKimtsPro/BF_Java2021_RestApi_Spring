package bstorm.akimts.restapi.controller;

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
    public void getHeader(@RequestHeader() HttpHeaders headers){
        headers.keySet().forEach(log::warn);
    }


}
