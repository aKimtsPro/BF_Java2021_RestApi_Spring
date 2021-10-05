package bstorm.akimts.restapi.util;

import bstorm.akimts.restapi.models.entity.User;
import bstorm.akimts.restapi.models.entity.Voiture;
import bstorm.akimts.restapi.repository.UserRepository;
import bstorm.akimts.restapi.repository.VoitureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseFiller implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(DatabaseFiller.class);
    private final VoitureRepository voitureRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(VoitureRepository repository, UserRepository userRepository, PasswordEncoder encoder) {
        this.voitureRepository = repository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("hydratation de la DB");

        List<Voiture> toInsert = List.of(
                new Voiture(0, "VrimVroom", "BipBoop", 120),
                new Voiture(0, "VrimVroom", "BipBoop-Mini", 80),
                new Voiture(0, "VrimVroom", "BipBoop Sprot", 180),
                new Voiture(0, "VrimVroom", "BipBoop-LuX", 200),
                new Voiture(0, "VrimVroom", "Breeh", 90),
                new Voiture(0, "VrimVroom", "Breeh 2", 110),
                new Voiture(0, "VrimVroom", "Breeh 2 Sprot", 150),
                new Voiture(0, "VrimVroom", "BipBoop 3", 100),
                new Voiture(0, "VrimVroom", "BipBoop 3 sw", 110),
                new Voiture(0, "VrimVroom", "BipBoop 4", 120)
        );

        voitureRepository.saveAll(toInsert);

        List<User> users = List.of(
                new User(0L, "admin", encoder.encode("pass"), List.of("ADMIN", "USER"), true, true, true,true ),
                new User(0L, "user", encoder.encode("pass"), List.of("USER"), true, true, true,true )
        );

        userRepository.saveAll(users);

    }
}
