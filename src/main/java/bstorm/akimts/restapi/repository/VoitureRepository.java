package bstorm.akimts.restapi.repository;

import bstorm.akimts.restapi.models.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
}
