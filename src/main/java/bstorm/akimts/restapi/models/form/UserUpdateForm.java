package bstorm.akimts.restapi.models.form;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.management.relation.Role;
import java.util.List;

@Data
@Builder
@Validated
public class UserUpdateForm {

    @Length(min = 8)
    private String password;
    private List<String> roles;

}
