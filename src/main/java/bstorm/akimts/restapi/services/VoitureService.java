package bstorm.akimts.restapi.services;

import bstorm.akimts.restapi.models.dto.VoitureDTO;
import bstorm.akimts.restapi.models.form.VoitureForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface VoitureService {

    public void insert(VoitureForm form);
    public VoitureDTO insertWithReturn(VoitureForm form);

    List<VoitureDTO> getAll();
    Page<VoitureDTO> getAllWithPagination(int page, int size);

    VoitureDTO getOne(Integer id);

    VoitureDTO delete(Integer id);

    VoitureDTO update(Integer id, VoitureForm form );

    VoitureDTO partialUpdate(Integer id, Map<String, Object> values);

}
