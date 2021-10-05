package bstorm.akimts.restapi.models.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterForm {

    @Length(min = 4, max = 20)
    private String username;
    @Length(min = 8)
    private String password;

}
