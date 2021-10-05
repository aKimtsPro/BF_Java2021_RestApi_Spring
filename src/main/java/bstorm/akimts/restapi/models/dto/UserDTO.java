package bstorm.akimts.restapi.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private List<String> roles;
    private String token;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean creditialsNonExpired;
    private boolean enabled;

}
