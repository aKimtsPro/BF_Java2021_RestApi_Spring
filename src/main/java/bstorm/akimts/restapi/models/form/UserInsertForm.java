package bstorm.akimts.restapi.models.form;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Data
@Builder
@Validated
public class UserInsertForm {

    @Length(min = 4, max = 20)
    private String username;
    @Length(min = 8)
    private String password;
    private List<String> roles;

}
