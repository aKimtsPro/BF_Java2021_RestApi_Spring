package bstorm.akimts.restapi.controller;

import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.form.UserInsertForm;
import bstorm.akimts.restapi.models.form.UserUpdateForm;
import bstorm.akimts.restapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertForm form){
        return ResponseEntity.ok( service.insert(form) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete( @PathVariable Long id ){
        return ResponseEntity.ok( service.delete(id) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateForm form){
        return ResponseEntity.ok(service.update(id, form));
    }

}
