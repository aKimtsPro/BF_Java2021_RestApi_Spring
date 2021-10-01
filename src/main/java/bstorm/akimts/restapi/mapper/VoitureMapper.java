package bstorm.akimts.restapi.mapper;

import bstorm.akimts.restapi.models.dto.VoitureDTO;
import bstorm.akimts.restapi.models.entity.Voiture;
import bstorm.akimts.restapi.models.form.VoitureForm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class VoitureMapper implements BaseMapper<VoitureDTO, VoitureForm, Voiture> {
    @Override
    public Voiture toEntity(VoitureDTO dto) {

        if(dto == null)
            return null;

        return new Voiture(
                dto.getId(),
                dto.getMarque(),
                dto.getModele(),
                dto.getPuissance()
        );
    }

    @Override
    public VoitureDTO toDto(Voiture entity) {

        if( entity == null)
            return null;

        VoitureDTO dto = new VoitureDTO();

        dto.setId(entity.getId());
        dto.setMarque(entity.getMarque());
        dto.setModele(entity.getModele());
        dto.setPuissance(entity.getPuissance());

        return dto;
    }

    @Override
    public Voiture fromFormToEntity(VoitureForm form) {
        if(form == null)
            return null;

        Voiture entity = new Voiture();

        entity.setMarque(form.getMarque());
        entity.setModele(form.getModele());
        entity.setPuissance(form.getPuissance());

        return entity;
    }
}
