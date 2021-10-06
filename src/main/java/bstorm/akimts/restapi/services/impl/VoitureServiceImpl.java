package bstorm.akimts.restapi.services.impl;

import bstorm.akimts.restapi.exceptions.models.VoitureNotFoundException;
import bstorm.akimts.restapi.mapper.VoitureMapper;
import bstorm.akimts.restapi.models.dto.VoitureDTO;
import bstorm.akimts.restapi.models.entity.Voiture;
import bstorm.akimts.restapi.models.form.VoitureForm;
import bstorm.akimts.restapi.repository.VoitureRepository;
import bstorm.akimts.restapi.services.VoitureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoitureServiceImpl implements VoitureService {

    private final VoitureRepository repository;
    private final VoitureMapper mapper;

    public VoitureServiceImpl(VoitureRepository repository, VoitureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insert(VoitureForm form) {

        Voiture entity = mapper.fromFormToEntity(form);
        repository.save(entity);

    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public VoitureDTO insertWithReturn(VoitureForm form) {
        Voiture entity = mapper.fromFormToEntity(form);
        VoitureDTO dto = mapper.toDto( repository.save(entity) );
        return dto;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<VoitureDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public VoitureDTO getOne(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow( () -> new VoitureNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public VoitureDTO delete(Integer id) {

        Voiture toDelete = repository.findById(id)
                .orElseThrow( () -> new VoitureNotFoundException(id));

        repository.delete( toDelete );

        return mapper.toDto(toDelete);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public VoitureDTO update(Integer id, VoitureForm form) {

        Voiture toUpdate = repository.findById(id)
                .orElseThrow( () -> new VoitureNotFoundException(id));

        toUpdate.setMarque(form.getMarque());
        toUpdate.setModele(form.getModele());
        toUpdate.setPuissance(form.getPuissance());

        repository.save(toUpdate);

        return mapper.toDto( toUpdate );
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public VoitureDTO partialUpdate(Integer id, Map<String, Object> values) {

        Voiture v = repository.findById(id)
                .orElseThrow(() -> new VoitureNotFoundException(id));

        for (String s : values.keySet()) {

            switch ( s ){
                case "marque":
                    String marque = (String) values.get(s);
                    if( marque == null || marque.isBlank() )
                        throw new IllegalArgumentException("valeur invalide pour marque");

                    v.setMarque( marque );
                    break;
                case "modele":
                    String modele = (String) values.get(s);
                    if( modele == null || modele.isBlank() )
                        throw new IllegalArgumentException("valeur invalide pour modele");

                    v.setModele( modele );

                    break;
                case "puissance":
                    Integer puissance = (Integer) values.get(s);
                    if( puissance < 20 || puissance > 250 )
                        throw new IllegalArgumentException("valeur invalide pour puissance");

                    v.setPuissance(puissance);
                    break;
            }
        }

        v = repository.save(v);
        return mapper.toDto(v);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Page<VoitureDTO> getAllWithPagination(int page, int size) {

        Page<Voiture> result = this.repository.findAll( PageRequest.of(page, size) );
        return result.map( mapper::toDto );

    }
}
