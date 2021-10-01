package bstorm.akimts.restapi.models.form;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Validated
@Data
public class VoitureForm {

    @NotBlank
    private String marque = "marque";
    @NotBlank
    private String modele = "modele";
    @Min(20) @Max(250)
    private Integer puissance = 150;

}
