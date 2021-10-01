package bstorm.akimts.restapi.controller;

import bstorm.akimts.restapi.models.dto.VoitureDTO;
import bstorm.akimts.restapi.models.entity.Voiture;
import bstorm.akimts.restapi.models.form.VoitureForm;
import bstorm.akimts.restapi.repository.VoitureRepository;
import bstorm.akimts.restapi.services.VoitureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voiture")
public class VoitureController {

    private final Logger log = LoggerFactory.getLogger(HelloController.class);
    private final VoitureService service;

    public VoitureController(VoitureService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public void addVoiture(@Valid @RequestBody VoitureForm form){

        log.info("ACCES POST : /voiture/add");
        log.info("voitureform envoyé : " + form);

        service.insert(form);
    }

    @PostMapping("/add_with_return")
    public ResponseEntity<VoitureDTO> addVoitureWithReturn(@Valid @RequestBody VoitureForm form){

        log.info("ACCES POST : /voiture/add_with_return");
        log.info("voitureform envoyé : " + form);

        VoitureDTO dto =  service.insertWithReturn(form);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoitureDTO> getOneVoiture( @PathVariable Integer id) {

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("custom_header", "value");

        VoitureDTO voitureDTO = service.getOne( id );

        return ResponseEntity
                .ok()
//                .status(HttpStatus.I_AM_A_TEAPOT)
//                .badRequest()
//                .ok( voitureDTO );
                .headers(headers)
                .body( voitureDTO );
    }

    @GetMapping(path= {"", "/all"})
    public ResponseEntity<List<VoitureDTO>> getAll(){
        List<VoitureDTO> list = service.getAll();
        return ResponseEntity.ok( list );
    }

    @GetMapping(params =  {"page","size"})
    private ResponseEntity<Page<VoitureDTO>> getAllWithPagination(@RequestParam int page, @RequestParam("size") int taille){

        Page<VoitureDTO> pagedVoitures = service.getAllWithPagination(page, taille);
        return ResponseEntity.ok(pagedVoitures);

    }

    @DeleteMapping(path = {"/{id}", "/delete/{id}"})
    public ResponseEntity<VoitureDTO> deleteVoiture( @PathVariable int id ){

        VoitureDTO dto = service.delete(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(path = {"/{id}","/update/{id}"})
    public ResponseEntity<VoitureDTO> updateVoiture( @PathVariable int id, @Valid @RequestBody VoitureForm form ){

        VoitureDTO dto = service.update(id, form);
        return ResponseEntity.ok(dto);

    }

    @PatchMapping({"/{id}", "/patch/{id}"})
    public ResponseEntity<VoitureDTO> patchVoiture(@RequestBody Map<String, Object> map, @PathVariable int id){

        VoitureDTO rslt = service.partialUpdate(id, map);
        return ResponseEntity.ok(rslt);

    }


}
