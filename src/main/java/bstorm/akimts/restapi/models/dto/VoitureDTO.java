package bstorm.akimts.restapi.models.dto;

import lombok.Data;

@Data
public class VoitureDTO {

    private Integer id;
    private String marque;
    private String modele;
    private Integer puissance;

}
