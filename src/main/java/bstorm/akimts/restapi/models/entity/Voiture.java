package bstorm.akimts.restapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voiture implements Serializable {

    public static long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marque;
    private String modele;
    private Integer puissance;

}
