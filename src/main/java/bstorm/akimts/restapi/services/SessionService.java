package bstorm.akimts.restapi.services;

import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.form.UserLoginForm;
import bstorm.akimts.restapi.models.form.UserRegisterForm;

public interface SessionService {

    UserDTO login(UserLoginForm form);
    UserDTO register(UserRegisterForm form);

}
