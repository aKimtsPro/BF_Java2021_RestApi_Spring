package bstorm.akimts.restapi.services.impl;

import bstorm.akimts.restapi.mapper.UserMapper;
import bstorm.akimts.restapi.models.dto.UserDTO;
import bstorm.akimts.restapi.models.entity.User;
import bstorm.akimts.restapi.models.form.UserInsertForm;
import bstorm.akimts.restapi.models.form.UserUpdateForm;
import bstorm.akimts.restapi.repository.UserRepository;
import bstorm.akimts.restapi.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserDetailsServiceImpl(UserMapper mapper, UserRepository repository, PasswordEncoder encoder) {
        this.mapper = mapper;
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDTO insert(UserInsertForm form) {
        User toInsert = mapper.fromInsertFormToEntity(form);
        toInsert.setAccountNonExpired(true);
        toInsert.setAccountNonLocked(true);
        toInsert.setCreditialsNonExpired(true);
        toInsert.setEnabled(true);

        toInsert.setPassword( encoder.encode(form.getPassword()) );

        toInsert = repository.save(toInsert);

        return mapper.toDto(toInsert);
    }

    @Override
    public UserDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur d'id {"+id+"} non trouvé"));
        // une exception spécifique peut être une bonne idée
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO update(Long id, UserUpdateForm form) {

        User toUpdate = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur d'id {"+id+"} non trouvé"));
        // une exception spécifique peut être une bonne idée

        toUpdate.setPassword( encoder.encode(form.getPassword()) );
        toUpdate.setRoles( form.getRoles() );

        toUpdate = repository.save(toUpdate);
        return mapper.toDto(toUpdate);
    }

    @Override
    public UserDTO delete(Long id) {

        User toDelete = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur d'id {"+id+"} non trouvé"));
        // une exception spécifique peut être une bonne idée

        repository.delete(toDelete);

        return mapper.toDto(toDelete);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur au username {"+username+"} n'a pas été trouvé."));
    }
}
