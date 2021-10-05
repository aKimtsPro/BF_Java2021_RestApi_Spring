package bstorm.akimts.restapi.controller;

import static bstorm.akimts.restapi.config.SecurityConstants.*;
import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.form.UserRegisterForm;
import bstorm.akimts.restapi.services.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    public ResponseEntity<UserDTO> login(){
        return null;
    }

    @PostMapping(REGISTER_URL)
    public ResponseEntity<UserDTO> register( @RequestBody UserRegisterForm form){
        return ResponseEntity.ok( service.register(form) );
    }

}
