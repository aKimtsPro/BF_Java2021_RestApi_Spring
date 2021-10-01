package bstorm.akimts.restapi.mapper;

import bstorm.akimts.restapi.models.entity.Voiture;

public interface BaseMapper<TDTO, TFORM, TENTITY> {

    TENTITY toEntity(TDTO dto);
    TDTO toDto(TENTITY entity);
    TENTITY fromFormToEntity(TFORM form);

}
