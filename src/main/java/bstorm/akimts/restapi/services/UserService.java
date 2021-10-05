package bstorm.akimts.restapi.services;

import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.form.UserInsertForm;
import bstorm.akimts.restapi.models.form.UserUpdateForm;
import bstorm.akimts.restapi.models.form.VoitureForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDTO insert(UserInsertForm form);

    UserDTO getOne(Long id);
    List<UserDTO> getAll();

    UserDTO update(Long id, UserUpdateForm form);

    UserDTO delete(Long id);



}
