package bstorm.akimts.restapi.services.impl;

import bstorm.akimts.restapi.config.jwt.JwtTokenProvider;
import bstorm.akimts.restapi.mapper.UserMapper;
import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.entity.User;
import bstorm.akimts.restapi.models.form.UserLoginForm;
import bstorm.akimts.restapi.models.form.UserRegisterForm;
import bstorm.akimts.restapi.repository.UserRepository;
import bstorm.akimts.restapi.services.SessionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final JwtTokenProvider provider;

    public SessionServiceImpl(PasswordEncoder encoder, UserRepository repository, UserMapper mapper, JwtTokenProvider provider) {
        this.encoder = encoder;
        this.repository = repository;
        this.mapper = mapper;
        this.provider = provider;
    }

    @Override
    public UserDTO login(UserLoginForm form) {
        return null;
    }

    @Override
    public UserDTO register(UserRegisterForm form) {

        User u = new User();
        u.setUsername(form.getUsername());
        u.setPassword( encoder.encode(form.getPassword()) );

        u.setRoles(List.of("USER"));

        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCreditialsNonExpired(true);
        u.setEnabled(true);

        u = repository.save(u);

        UserDTO dto = mapper.toDto(u);
        dto.setToken( provider.createToken(dto.getUsername(), dto.getRoles()) );

        return dto;
    }
}
