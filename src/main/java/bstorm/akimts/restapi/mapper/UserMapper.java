package bstorm.akimts.restapi.mapper;

import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.entity.User;
import bstorm.akimts.restapi.models.form.UserInsertForm;
import bstorm.akimts.restapi.models.form.UserUpdateForm;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO toDto(User entity){

        if(entity == null)
            return null;

        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .accountNonExpired(entity.isAccountNonExpired())
                .accountNonLocked(entity.isAccountNonLocked())
                .creditialsNonExpired(entity.isCreditialsNonExpired())
                .enabled(entity.isEnabled())
                .build();
    }

    public User toEntity(UserDTO dto){
        if(dto == null)
            return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setRoles(dto.getRoles());
        user.setAccountNonExpired(dto.isAccountNonExpired());
        user.setAccountNonLocked(dto.isAccountNonLocked());
        user.setCreditialsNonExpired(dto.isCreditialsNonExpired());
        user.setEnabled(dto.isEnabled());

        return user;
    }

    public User fromInsertFormToEntity(UserInsertForm form){
        if( form == null)
            return null;

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setRoles(form.getRoles());

        return user;
    }

    public User fromUpdateFormToEntity(UserUpdateForm form){
        if( form == null)
            return null;

        User user = new User();
        user.setPassword(form.getPassword());
        user.setRoles(form.getRoles());

        return user;
    }


}
